package cn.internalaudit.audit.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.internalaudit.audit.entitys.BaseEntity;

public class TreeModel<E extends BaseEntity> {

	public JSONObject createTreeNode(E e) throws Exception {
		JSONObject node = new JSONObject();

		// Map map = new HashMap();
		Class eclass = e.getClass();
		Field[] fields = eclass.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			if (f.getType() == String.class || f.getType() == Double.class
					|| f.getType() == double.class
					|| f.getType() == Long.class
					|| f.getType() == long.class
					|| f.getType() == Integer.class
					|| f.getType() == int.class
					|| f.getType() == Boolean.class
					|| f.getType() == boolean.class) {
				node.put(f.getName(), f.get(e));
				if (f.getName().equals("name")) {
					node.put("text", f.get(e));
				}
				if(f.getName().equals("code")){
					node.put("code", f.get(e));
				}

			}
		}
		Method mothed = eclass.getDeclaredMethod("getChildren");
		List<E> children = (List<E>) mothed.invoke(e);
		if (children.size() > 0) {
			node.put("hasChildren", true);
			node.put("children", createTreeNodes(children));
		}
		eclass = eclass.getSuperclass();
		if (eclass != null) {
			fields = eclass.getDeclaredFields();
			for (Field f : fields) {
				f.setAccessible(true);
				try {
					if (f.getType() == String.class
							|| f.getType() == Double.class
							|| f.getType() == Long.class
							|| f.getType() == Integer.class
							|| f.getType() == int.class) {
						node.put(f.getName(), f.get(e));
						if (f.getName().equals("id")) {
							node.put("nid", String.valueOf(f.get(e)));
							node.put("id", String.valueOf(f.get(e)));
						}
						
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}

		return node;
	}
	
	

	public JSONObject createTreeNode(E e, Class lastClass) throws Exception {
		JSONObject node = new JSONObject();

		Class eclass = e.getClass();
		Field[] fields = eclass.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			if (f.getType() == String.class || f.getType() == Double.class
					|| f.getType() == double.class
					|| f.getType() == Long.class
					|| f.getType() == long.class
					|| f.getType() == Integer.class
					|| f.getType() == int.class
					|| f.getType() == Boolean.class
					|| f.getType() == boolean.class) {
				node.put(f.getName(), f.get(e));
				if (f.getName().equals("name")) {
					node.put("text", f.get(e));
				}

			}
		}
		Method mothed = null;
		List<E> children = null;
		if (eclass != lastClass) {
			mothed = eclass.getDeclaredMethod("getAllChildren");
		}else{
			mothed = eclass.getDeclaredMethod("getChildren");
		}
		children = (List<E>) mothed.invoke(e);
		if (children.size() > 0) {
			node.put("hasChildren", true);
			node.put("children", createTreeNodes(children,lastClass));
		}
		eclass = eclass.getSuperclass();
		if (eclass != null) {
			fields = eclass.getDeclaredFields();
			for (Field f : fields) {
				f.setAccessible(true);
				try {
					if (f.getType() == String.class
							|| f.getType() == Double.class
							|| f.getType() == Long.class
							|| f.getType() == Integer.class
							|| f.getType() == int.class) {
						node.put(f.getName(), f.get(e));
						if (f.getName().equals("id")) {
							node.put("nid", f.get(e));
						}
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}

		return node;
	}

	public JSONArray createTreeNodes(List<E> list) throws Exception {
		JSONArray nodes = new JSONArray();
		for (E e : list) {
			JSONObject node = createTreeNode(e);
			if(node.get("code")!=null&&node.get("text")!=null){
				node.put("text",node.get("code").toString()+" "+node.get("text").toString());
			}
			nodes.add(node);
		}
		return nodes;
	}
//	public JSONArray createTreeNodesToBusinessMode(List<E> list) throws Exception {
//		JSONArray nodes = new JSONArray();
//		for (E e : list) {
//			JSONObject node = createTreeNode(e);
//			if(node.get("code")!=null&& node.get("name")!=null){
//				node.put("text",node.get("code").toString()+node.get("name").toString());
//			}
//			nodes.add(createTreeNode(e));
//		}
//		return nodes;
//	}


	public JSONArray createTreeNodes(List<E> list, Class lastClass)
			throws Exception {
		JSONArray nodes = new JSONArray();
		for (E e : list) {
			nodes.add(createTreeNode(e, lastClass));
		}
		return nodes;
	}
}
