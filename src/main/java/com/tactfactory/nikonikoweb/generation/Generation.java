package com.tactfactory.nikonikoweb.generation;

import org.hibernate.Session;

import com.tactfactory.nikonikoweb.models.Function;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.utils.HibernateUtil;

public class Generation {
	public static void main2(String[] args) {
		User user = new User();
		Function function = new Function();

		Session session = HibernateUtil.getSessionFactory().openSession();

		String[] functions = { "administrateur","vip", "développeur", "chef de projet" };
		for(String current: functions) {
			function = new Function();
			function.setName(current);
			session.save(function);
		}


		System.out.println(function);
	}
}
