package engtelecom.std.labrest.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import engtelecom.std.labrest.entities.Saudacao;

@RestController 
public class SaudacaoController {
    
    // para evitar condicao de disputa;
    // caso de que mais de um cliente acesse no mesmo instante de tempo
    private AtomicInteger contador = new AtomicInteger();

    @GetMapping("/saudacao")
    public Saudacao ola(@RequestParam(value = "nome", defaultValue = "mundo") String nome){
        return new Saudacao(contador.incrementAndGet(), "Olá " + nome);
    }




}
