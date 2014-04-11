package cn.internalaudit.audit.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.internalaudit.audit.entitys.BaseEntity;

public class DataModel<E extends BaseEntity> {

	private int total;
	private JSONArray rows = new JSONArray();

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public JSONArray getRows() {
		return rows;
	}

	public void setRows(JSONArray rows) {
		this.rows = rows;
	}

	public JSONArray setRows(List<E> list) {
		this.total = list.size();
	
		for (E e : list) {
			rows.add(createRow(e));
		}
		return rows;
	}
	private Object createRow(E e){
		Map map = new HashMap();
		Class eclass = e.getClass();
		Field[] fields = eclass.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			try {
				if (f.getType() == String.class
						|| f.getType() == Double.class
						|| f.getType() == double.class
						|| f.getType() == Long.class
						|| f.getType() == long.class
						|| f.getType() == Integer.class
						|| f.getType() == int.class
						|| f.getType() == Boolean.class
						|| f.getType() == boolean.class) {
					map.put(f.getName(), String
							.valueOf(f.get(e) == null ? "" : f.get(e)));

					if (f.getName().equals("name")) {
						map.put("text", f.get(e));
					}
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		eclass = eclass.getSuperclass();
		if (eclass != null) {
			fields = eclass.getDeclaredFields();
			for (Field f : fields) {
				try {
					f.setAccessible(true);
					if (f.getType() == String.class
							|| f.getType() == Double.class
							|| f.getType() == Long.class
							|| f.getType() == Integer.class
							|| f.getType() == int.class) {
						map.put(f.getName(), String
								.valueOf(f.get(e) == null ? "" : f.get(e)));
						if (f.getName().equals("id")) {
							map.put("value", f.get(e));
						}
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		return JSONObject.fromObject(map);
	}
	public void copy(Object sourceEntity, Object targetEntity) throws Exception {
		Class eclass = sourceEntity.getClass();
		Class e1class = targetEntity.getClass();
		Field[] fields = eclass.getDeclaredFields();
	//	Field[] fields1 = e1class.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			if (fields[i].getType() == String.class
					|| fields[i].getType() == Double.class
					|| fields[i].getType() == double.class
					|| fields[i].getType() == Long.class
					|| fields[i].getType() == long.class
					|| fields[i].getType() == Integer.class
					|| fields[i].getType() == int.class
					|| fields[i].getType() == Boolean.class
					|| fields[i].getType() == boolean.class) {
				Field field = e1class.getDeclaredField(fields[i].getName());
				field.setAccessible(true);
				field.set(targetEntity, fields[i].get(sourceEntity));
			}
			
		}
		
		eclass = eclass.getSuperclass();
		e1class = e1class.getSuperclass();
		
		Field field = eclass.getDeclaredField("id");
		field.setAccessible(true);
		
		Method method = e1class.getDeclaredMethod("setId", Long.class);
		
		method.invoke(targetEntity,field.get(sourceEntity));
		
	}
	
}
