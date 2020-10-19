package co.edu.javeriana.services.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class TallerController {

    @Value("${servidor.title}")
    private String description;

    @Value("${servidor.university}")
    private String university;

    @Value("${servidor.info.title}")
    private String title;

    @Value("${servidor.info.name}")
    private String name;

    @Value("${servidor.info.username}")
    private String username;

    @RequestMapping("/")
    public String index(Model model){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String datetime = now.format(formatter);

        InetAddress ip = null;
        String hostname = "";
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();

            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        model.addAttribute("university", university);
        model.addAttribute("description", description);

        model.addAttribute("title", title);
        model.addAttribute("name", hostname);
        model.addAttribute("ip", ip.getHostAddress());
        model.addAttribute("username", username);
        model.addAttribute("datetime", datetime);

        return "index";
    }

}
