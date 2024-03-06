package com.cbsinc.cms.faceds;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

	final private static Logger log = Logger.getLogger(RedisUtils.class);
	final ResourceBundle setup_resources = PropertyResourceBundle.getBundle("appconfig");

	JedisPool jedisPool;

	static RedisUtils redisUtils = new RedisUtils();
	
	static public RedisUtils getInstance()
	{
		return redisUtils;
	}

	private JedisPoolConfig buildPoolConfig() {

		String redis_max_total = setup_resources.getString("redis_max_total");
		redis_max_total = redis_max_total == null ? "128" : redis_max_total;

		String redis_max_idle = setup_resources.getString("redis_max_idle");
		redis_max_idle = redis_max_idle == null ? "128" : redis_max_idle;

		String redis_min_idle = setup_resources.getString("redis_min_idle");
		redis_min_idle = redis_min_idle == null ? "16" : redis_min_idle;

		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(Integer.parseInt(redis_max_total));
		poolConfig.setMaxIdle(Integer.parseInt(redis_max_idle));
		poolConfig.setMinIdle(Integer.parseInt(redis_max_idle));
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);
		poolConfig.setTestWhileIdle(true);
		poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
		poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
		poolConfig.setNumTestsPerEvictionRun(3);
		poolConfig.setBlockWhenExhausted(true);
		return poolConfig;
	}

	private RedisUtils() {
		String redis_host = setup_resources.getString("redis_host");
		redis_host = redis_host == null ? "localhost" : redis_host;
		String redis_post = setup_resources.getString("redis_post");
		redis_post = redis_post == null ? "6379" : redis_post;

		final JedisPoolConfig poolConfig = buildPoolConfig();
		jedisPool = new JedisPool(poolConfig, redis_host, Integer.parseInt(redis_post));
	}

	public byte[] getKey(String filePath, String fileName) {
		byte[] key = (filePath + fileName).getBytes();
		return key;

	}
	
	public byte[] getKey(String fileNameAsStoreKey) {
		byte[] key = fileNameAsStoreKey.getBytes();
		return key;

	}


	/**
	 * 
	 * @param fileURL
	 * @param fileNameAsStoreKey
	 */
	public void writeFileInRedis(String fileURL , String fileNameAsStoreKey) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			byte[] bytes = Files.readAllBytes(new File(fileURL).toPath());
			log.info("Setting bytes of length:" + bytes.length + " got from file " + fileURL + "in redis.");
			byte[] key = getKey(fileNameAsStoreKey);
			if(bytes == null || bytes.length == 0 || key == null || key.length == 0 ) 
			{
				Exception ex = new Exception("Setting bytes of length:" + bytes.length + " got from file " + fileURL + "in redis. Key = " + new String(key) ) ;
				log.error(ex.getMessage(), ex);
				throw ex ;
				
			}
			jedis.set(key, bytes);

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}
	

	


	public byte[] readAndWriteFileInFS(String fileURL,  byte[] key) {
		byte[] retrievedBytes = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			retrievedBytes = jedis.get(key);
			System.out.println("Saving data in file " + fileURL);
			Files.write(Paths.get(fileURL), retrievedBytes);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return retrievedBytes;
	}

	/*
	public byte[] readAndWriteFileInFS(String filePath, String newFileName, byte[] key) {
		byte[] retrievedBytes = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			retrievedBytes = jedis.get(key);
			System.out.println("Saving data in file " + filePath + newFileName);
			Files.write(Paths.get(filePath + newFileName), retrievedBytes);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return retrievedBytes;
	}
	
	
	public void writeFileInFS(String filePath, String newFileName, byte[] key) {
		byte[] retrievedBytes = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			System.out.println("Saving data in file " + filePath + newFileName);
			Files.write(Paths.get(filePath + newFileName), retrievedBytes);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	*/
	
	public void writeFileInFS(String fileURL,  byte[] key) {
		byte[] retrievedBytes = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			retrievedBytes = jedis.get(key);
			if(retrievedBytes == null || retrievedBytes.length == 0 || key == null || key.length == 0 ) 
			{
				Exception ex = new Exception("Retrieved bytes of length:" + retrievedBytes.length + " got from file " + fileURL + "in redis. Key = " + new String(key) ) ;
				log.error(ex.getMessage(), ex);
				throw ex ;
				
			}

			System.out.println("Saving data in file " + fileURL +  " key = " + new String(key));
			Files.write(Paths.get(fileURL), retrievedBytes);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	
	public byte[] readeFileFromRedis(byte[] key) {

		byte[] retrievedBytes = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			retrievedBytes = jedis.get(key);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return retrievedBytes;
	}

}
