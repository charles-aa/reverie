package com.qst.chapter07;

import java.io.File;
import java.io.FileInputStream;

class Animal{
	public void say() {
		System.out.println("这是一个Animal类");
	}
}
public class MyClassLoader extends ClassLoader{	
	public Class<?> findClass(String className){
		byte[] data=loadClassData(className);
		return this.defineClass(className, data,0, data.length);
	}
	public byte[] loadClassData(String className) {
		try {
			String path=this.getClass().getResource("/").getPath();
			path=path.substring(1);
			className=className.replace(".", "/");
			File classfile=new File(path+className+".class");
			long len=classfile.length();
			byte[] raw=new byte[(int)len];
			FileInputStream fin=new FileInputStream(classfile);
			int r=fin.read(raw);
			if(r!=len) {
				System.out.println("无法读取全部文件！");
				return null;
			}else {
				return raw;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String []args)throws InstantiationException,IllegalAccessException,ClassNotFoundException{
		MyClassLoader mcl=new MyClassLoader();
		Class<?>clazz=mcl.loadClass("com.qst.chapter07.Animal");
		Animal animal=(Animal)clazz.newInstance();
		animal.say();
	}
}
