package com.example.taco_cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j //provides a logger class, and add it to our class, its Lombok annotation
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")//a attribute that is carried in the session, and can span in multiple requests
public class DesignTacoController {

    @ModelAttribute //this annotation has 2 uses, in this case the method level, means that before we even do our requeest mapping, we are gonna invoke these methods, and add what we return into our model, in this case, we didnt return anything but added Model model instance and added multiple lists of types
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
        Type[] types = Type.values(); //list all enum types
        for(Type type: types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(ingredients, type)); //separate types and put them in separate lists
        }
    }
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping()
    public String showDesignForm() {
        return "design"; //return name of view
    }
    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x-> x.getType().equals(type)) //get all types that match parameter type
                .collect(Collectors.toList()); //return a list of all desired types
    }
}
