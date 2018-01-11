package com.fxs;



import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;



/**
 * 轮询算法  15个人访问5个端口
 * @author 方小顺
 *
 */
public class TestZookeeper {
	
	private static String CONNECT_STRING ="192.168.111.128:2181";
	private static String PATH ="/fxs";
	private static Logger logger =Logger.getLogger(TestZookeeper.class);
	
	
	
	public static void main(String[] args) throws Exception {
		//连接zookeeper客户端
		ZooKeeper startZK = startZK();
		//模拟十五个消费者
		for(int i=1;i<=15;i++) {
			String node = getNode(startZK,"/k"+i);
			System.out.println("消费者 "+i+" :   " +node);
		}  
		
		Thread.sleep(Long.MAX_VALUE);
	}
	/**
	 * 连接zookeeper，一次回话开始
	 * @return
	 * @throws Exception
	 */
	public static ZooKeeper startZK() throws Exception {
		ZooKeeper zooKeeper = new ZooKeeper(CONNECT_STRING,20*1000, new Watcher() {
			
			@Override
			public void process(WatchedEvent arg0) {
				System.out.println("观察者正在观察");
			}
		});
		return zooKeeper;
	}
	/**
	 * 创造节点
	 * @param zooKeeper
	 * @throws Exception
	 */
	public static void createNode(ZooKeeper zooKeeper,String path,String str) throws Exception {
		
		zooKeeper.create(path, str.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}
	/**
	 * 获取节点
	 * @param zooKeeper
	 * @return
	 * @throws Exception
	 */
	public static String getNode(ZooKeeper zooKeeper,String path) throws Exception {
		
		
		byte[] data = zooKeeper.getData(path, new Watcher() {
			
			@Override
			public void process(WatchedEvent arg0) {
				try {
					watch(zooKeeper,path);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, new Stat());
		String result = new String(data);
		
		return result;
	}
	
	/**
	 * 关闭回话
	 * @param zooKeeper
	 * @throws Exception
	 */
	public static void closeZk(ZooKeeper zooKeeper) throws Exception {
		if(zooKeeper != null) {
			zooKeeper.close();
		}
	}
	
	public static void watch(ZooKeeper zooKeeper,String path) throws Exception {
		
		byte[] data = zooKeeper.getData(path, new Watcher() {

			@Override
			public void process(WatchedEvent arg0) {
				try {
					watch(zooKeeper,path);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}, new Stat());
		
		String newValue = new String(data);
		System.out.println("新的值为 : " + newValue);
		
		
	}
	
}	
