package servlets; //change to your situation!
import config.WebConfig; //change to your situation!
import models.Animal;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ListServlet", urlPatterns = "/list", loadOnStartup = 1)
public class ListServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Initializing Thymeleaf template engine");
        final ServletContext servletContext = this.getServletContext();
        WebConfig.createTemplateEngine(servletContext);
    }
    private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        process(request, response);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        process(request, response);
    }

    public ArrayList<Animal> createAnimals(){
        ArrayList<Animal> animals = new ArrayList<Animal>();
        Animal cow = new Animal();
        cow.set("Cow", "Venezuela");
        Animal fox = new Animal();
        fox.set("Fox", "Germany");
        animals.add(cow);
        animals.add(fox);
        return animals;
    }
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //this step is optional; standard settings also suffice
        WebConfig.configureResponse(response);
        WebContext ctx = new WebContext(
                request,
                response,
                request.getServletContext(),
                request.getLocale());
        ArrayList<Animal> animals = createAnimals();
        ArrayList<String> names = new ArrayList<String>();
        for (Animal animal: animals) {
            names.add(animal.getName());
        }
        ctx.setVariable("currentDate", new Date());
        ctx.setVariable("namesList", names);
        ctx.setVariable("name_of_bird_selector", "fav_birds");
        ctx.setVariable("bird_groups", List.of("raptors", "songbirds", "waders", "wildfowl"));
        WebConfig.createTemplateEngine(getServletContext()).
                process("list", ctx, response.getWriter());
    }
}