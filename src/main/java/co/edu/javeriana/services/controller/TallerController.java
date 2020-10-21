package co.edu.javeriana.services.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

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
        String ipAddress = "";
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();

            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while(e.hasMoreElements())
            {
                NetworkInterface n = (NetworkInterface) e.nextElement();

                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements())
                {
                    ip = (InetAddress) ee.nextElement();

                    if(ip instanceof Inet4Address && !ip.isLoopbackAddress())
                    {
                        if (n.getName().equalsIgnoreCase("ham0")) {
                            System.out.println(n.getName() + " - " + ip.getHostAddress());
                            ipAddress = ip.getHostAddress();
                            break;
                        }
                    }

                }
            }

        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }

        System.out.println("Your current IP address : " + ipAddress);
        System.out.println("Your current Hostname : " + hostname);

        model.addAttribute("university", university);
        model.addAttribute("description", description);

        model.addAttribute("title", title);
        model.addAttribute("name", hostname);
        model.addAttribute("ip", ipAddress);
        model.addAttribute("username", username);
        model.addAttribute("datetime", datetime);

        return "index";
    }

}
