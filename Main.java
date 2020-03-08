package testing;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
	public static void main(String args[]) throws IllegalArgumentException, IllegalAccessException {
		System.out.println("Hello. We will create an object from Class Mensagem and then"
				+ " we will print all of its fields and the respective values.");

		List<Long> numbers = new ArrayList<>();
		numbers.add(1L);
		numbers.add(2L);
		numbers.add(3L);
		numbers.add(4L);
		InBaseX inBaseX = new InBaseX("testeaaa");
		List<InBaseX> listInBaseX = new ArrayList<>();
		listInBaseX.add(new InBaseX(null));
		listInBaseX.add(new InBaseX("b"));
		listInBaseX.add(new InBaseX("c"));
		BaseX bx = new BaseX(numbers, inBaseX, listInBaseX, null);
		List<BaseY> by = new ArrayList<>();
		by.add(new BaseY("asd"));
		by.add(new BaseY("fg"));
		Mensagem m = new Mensagem("xdxdxdxdxdx", 10, 10L, bx, by);
		
		//printAll(m, "");
		
		List<Long> numbers2 = new ArrayList<>();
		numbers2.add(5L);
		numbers2.add(6L);
		numbers2.add(8L);
		numbers2.add(9L);
		InBaseX inBaseX2 = new InBaseX("eeerewrw");
		List<InBaseX> listInBaseX2 = new ArrayList<>();
		listInBaseX2.add(new InBaseX("xd"));
		listInBaseX2.add(new InBaseX("ppp"));
		listInBaseX2.add(new InBaseX("nig"));
		BaseX bx2 = new BaseX(numbers2, inBaseX2, listInBaseX2, numbers2);
		List<BaseY> by2 = new ArrayList<>();
		by2.add(new BaseY("34345"));
		by2.add(new BaseY("2234"));
		Mensagem m2 = new Mensagem("kkkkkkkk", 20, 30L, bx2, by2);
		
		printAllTwoObjects(m, m2, "");
		
	}
	
	
public static void printAll(Object object, String parentField) throws IllegalArgumentException, IllegalAccessException {
	Field fields[] = object.getClass().getDeclaredFields();
	for(Field f : fields) {
		String objectField = parentField.equals("") ? f.getName() : parentField + "_" + f.getName();
		
		f.setAccessible(true);
		if(f.getType().getSimpleName().equals("List")) {
			//its a list. Check if list is initialized (!= null)
			if(!Objects.isNull(((List)f.get(object)))) {
				// its initialized. Get first element to check type.
				Object o = ((List)f.get(object)).get(0);
				if(isOneOfMyClasses(o.getClass())){
					for(int i = 0; i < ((List)f.get(object)).size(); i++) {
						printAll(((List)f.get(object)).get(i), objectField);
					}
				}
				else {
					for(int i = 0; i < ((List)f.get(object)).size(); i++) {
						System.out.println(objectField + "_" + i + "-> " + ((List)f.get(object)).get(i) + " | Type: " +  ((List)f.get(object)).get(i).getClass().getSimpleName());
					}
				}	
			}
		}
		else if(isOneOfMyClasses(f.getType())){
			printAll(f.get(object), objectField);
		}
		else {
		System.out.println(objectField + " -> " + f.get(object) + " | Type: " + f.getType().getSimpleName());
		}
	}

}

public static void printAllTwoObjects(Object objectA, Object objectB, String parentField) throws IllegalArgumentException, IllegalAccessException {
	String sa;
	String sb;
	Field fields[] = objectA.getClass().getDeclaredFields();
	for(Field f : fields) {
		String objectField = parentField.equals("") ? f.getName() : parentField + "_" + f.getName();
		
		f.setAccessible(true);
		if(f.getType().getSimpleName().equals("List")) {
			//its a list. Check if list is initialized (!= null)
			if(!Objects.isNull(((List)f.get(objectA))) || !Objects.isNull(((List)f.get(objectB)))) {
				Object x = null;
				if(Objects.isNull(((List)f.get(objectA)))){
					f.set(objectA, createEmptyListofType((List)f.get(objectB)));
					x = ((List)f.get(objectB)).get(0);
				}
				if(Objects.isNull(((List)f.get(objectB)))){
					f.set(objectB, createEmptyListofType((List)f.get(objectA)));
					x = ((List)f.get(objectA)).get(0);
				}
				
				Object o = x == null ? ((List)f.get(objectA)).get(0) : x;
				// its initialized. Get first element to check type.
				
				if(isOneOfMyClasses(o.getClass())){
					for(int i = 0; i < ((List)f.get(objectA)).size(); i++) {
						printAllTwoObjects(((List)f.get(objectA)).get(i), ((List)f.get(objectB)).get(i), objectField);
					}
				}
				else {
					for(int i = 0; i < ((List)f.get(objectA)).size(); i++) {
						sa = objectField + "_" + i + "-> " + ((List)f.get(objectA)).get(i);
						sb = objectField + "_" + i + "-> " + ((List)f.get(objectB)).get(i);
						System.out.println(sa + " | " + sb);
					}
				}	
			}
		}
		else if(isOneOfMyClasses(f.getType())){
			printAllTwoObjects(f.get(objectA), f.get(objectB), objectField);
		}
		else {
			sa = objectField + " -> " + f.get(objectA);
			sb = objectField + " -> " + f.get(objectB);
			System.out.println(sa + " | " + sb);
		}
	}

}

public static <T> List<T> createEmptyListofType(List<T> objectB){
	List<T> copy = objectB.stream().collect(Collectors.toList());
	for(int i = 0; i < copy.size(); i++) {
		copy.set(i, null);
	}
	return copy;
}

public static boolean isPrimitiveOrPrimitiveWrapperOrString(Class<?> type) {
    return (type.isPrimitive() && type != void.class) ||
        type == Double.class || type == Float.class || type == Long.class ||
        type == Integer.class || type == Short.class || type == Character.class ||
        type == Byte.class || type == Boolean.class || type == String.class;
}

public static boolean isOneOfMyClasses(Class<?> type) {
	return type == BaseX.class || type == BaseY.class || type == InBaseX.class;
}

}
