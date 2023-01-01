package javdev.procesarorden.controller;

import javdev.procesarorden.consumer.ResultadoConsumer;
import javdev.procesarorden.entities.Cliente;
import javdev.procesarorden.entities.Item;
import javdev.procesarorden.entities.Orden;
import javdev.procesarorden.producer.OrdenProducer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/orden")
public class ProcesarOrdenController {
    private final OrdenProducer ordenProducer;
    private Orden ordenEnviar;
    Cliente cliente = new Cliente("C001","Pepito","1234567891011");
    @Value("${kafka.request.topic}")
    private String requestTopic;
    @Value("${kafka.reply.topic}")
    private String replyTopic;
    private String resultado = "";

    @GetMapping("")
    public String home(){
        return "home";
    }
    @GetMapping("/registrar")
    public String vistaRegistrar(Model model){
        List<Item> items = new ArrayList<>();
        items.add(new Item("AR001","Papel Bond XEROX",5,18.9));
        items.add(new Item("AR002","Lapices VINIFAN",5,6.4));
        items.add(new Item("AR004","Cuaderno MINERVA",5,11.9));
        items.add(new Item("AR006","Libreta de Apuntes",5,19.0));
        double total = 0.0;
        for(Item i :items){
            total += i.getCantidad()*i.getPrecio();
        }
        String fecha = "";
        ordenEnviar = new Orden("006",items,cliente,fecha,total);
        model.addAttribute("orden", ordenEnviar);
        return "orden";
    }
    @PostMapping("/enviar")
    public String enviarOrden(@ModelAttribute Orden orden){
        ordenEnviar.setFecha(orden.getFecha());
        ordenProducer.send(ordenEnviar);
        return "redirect:estado";
    }
    @GetMapping("/estado")
    public String mostrarEstado(){
        return "show";
    }

}
