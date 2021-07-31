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

		// Crea un objeto de preferencia
		Preference preference = new Preference();

		// Crea un ítem en la preferencia
			Item item = new Item();
			item.setTitle("Suscripción")
					.setQuantity(1)
					.setUnitPrice((float) 1);
			preference.appendItem(item);
		try {
			preference.save();
			System.out.println("preference id: " + preference.getId());
		} catch (MPException e) {
			System.out.println("Error al guardar preference item");
		}


		SpringApplication.run(CoreApplication.class, args);
	}

}
