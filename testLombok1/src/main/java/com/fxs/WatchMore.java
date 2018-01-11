package com.fxs;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * 轮询算法 15个人访问5个端口
 * 
 * @author 方小顺
 *
 */
public class WatchMore {

	private static String CONNECT_STRING = "192.168.179.128:2181";
	private static String PATH = "/bank/k";
	private static String str = "hahaha01********";
	private static Logger logger = Logger.getLogger(WatchMore.class);

	private String oldValue = "";
	
	private static int count = 1;

	public static void main(String[] args) throws Exception {
		ZooKeeper zk = startZK();
		// 模拟十五个消费者
		for (int i = 1; i <= 15; i++) {
			Thread.sleep(2*1000);
			String node = getNode(zk, PATH, i);
//			System.out.println("消费者 " + i + " :   " + node);
		}

	}

	/**
	 * 连接zookeeper，一次回话开始
	 * 
	 * @return
	 * @throws Exception
	 */
	public static ZooKeeper startZK() throws Exception {
		ZooKeeper zooKeeper = new ZooKeeper(CONNECT_STRING, 20 * 1000, new Watcher() {

			@Override
			public void process(WatchedEvent arg0) {
			}
		});
		return zooKeeper;
	}

	/**
	 * 创造节点
	 * 
	 * @param zooKeeper
	 * @throws Exception
	 */
	public static void createNode(ZooKeeper zooKeeper, String str) throws Exception {

		zooKeeper.create(PATH, str.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}

	/**
	 * 获取节点
	 * 
	 * @param zooKeeper
	 * @return
	 * @throws Exception
	 */
	public static String getNode(ZooKeeper zooKeeper, String path, int index) throws Exception {
		
		String result = null;
		
		for(int i=1;i<5;i++) {
			//这里有五台机子
			Stat stat = zooKeeper.exists(path+count, false);
			
			if(stat!=null) {
				byte[] data = zooKeeper.getData(path+count, false, new Stat());
				result = new String(data);
				
				System.out.println("消费者"+index + "在   ： " + count +"处理");
				count +=1;
				
				return result;
				
			}else {
				count +=1;
				if(count>5) {
					count=1;
				}
			}
			
		}
		return result;
		

		
	}

	/**
	 * 关闭回话
	 * 
	 * @param zooKeeper
	 * @throws Exception
	 */
	public static void closeZk(ZooKeeper zooKeeper) throws Exception {
		if (zooKeeper != null) {
			zooKeeper.close();
		}
	}

	public static void watch() {
		System.out.println("2222");
		logger.info("观察者被调用");
	}

}
