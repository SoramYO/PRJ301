/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonnx.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Son
 */
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying............");
        //1. get context scope
        ServletContext context = sce.getServletContext();
        //2. read file properties
        String filePath = context.getInitParameter("SITEMAPS_FILE_PATH");
        Properties siteMaps = new Properties();
        InputStream is = null;
        try {
            is = context.getResourceAsStream(filePath);
            siteMaps.load(is);
            //3. catching on context scope
            context.setAttribute("SITEMAPS", siteMaps);
        } catch (IOException ex) {
            context.log("MyContextListener _ IOException: " + ex.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    context.log("MyContextListener _ IOException: " + ex.getMessage());
                }
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Say goodbye");
    }
}
