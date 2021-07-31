package com.emcove.rest.api.Core;

import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;

import com.mercadopago.resources.datastructures.preference.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import com.mercadopago.MercadoPago;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		// SDK de Mercado Pago

		try {
			MercadoPago.SDK.setAccessToken(System.getenv("MP_ACCESS_TOKEN"));
		} catch (MPConfException e) {
			System.out.println("Error al setear MP access token");
		}

		SpringApplication.run(CoreApplication.class, args);
	}

}
