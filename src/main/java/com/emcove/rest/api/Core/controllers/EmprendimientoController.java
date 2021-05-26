package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.response.Emprendimiento;
import com.emcove.rest.api.Core.response.Usuario;
import com.emcove.rest.api.Core.utilities.ResponseUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entrepreneurship")
public class EmprendimientoController {
    @GetMapping("/{id}")
    public String getEntreprenuership(@PathVariable Integer id){
        Emprendimiento entreprenuership = new Emprendimiento(id,"Rome","Pasteleria y desayunos.");
        return ResponseUtils.toJson(entreprenuership);
    }

    @DeleteMapping("/{id}")
    public String deleteEntreprenuership(@PathVariable Integer id){
        return "DeleteEntreprenuership: " + id;
    }

    @PostMapping("/")
    public String createEntreprenuership(@RequestParam String name, @RequestParam String description){
        Emprendimiento entreprenuership = new Emprendimiento(1,name,description);

        return "Entreprenuership create with success. entreprenuership:" + ResponseUtils.toJson(entreprenuership);
    }
}
