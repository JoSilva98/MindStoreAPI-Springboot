package MindStore.dataloader.ProductsFetch;

import MindStore.dataloader.ProductsFetch.ApiProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor //so nao cria as variaveis que sao final, usar sempre que nao temos constantes
@RestController
@RequestMapping("/api/v1")
public class ExternalAPIController {

    //esta no beans init
    private final RestTemplate restTemplate;

    String url = "https://fakestoreapi.com/products/1";

    @GetMapping(path = "/api")
    public ResponseEntity<ApiProduct> getRecipeByIngredientName() {

        String finalUri = url;
        System.out.println("finalUri = " + finalUri);
        //sempre que chamarmos este Get, vamos ter a recipe -> "apiRecipe"
        ApiProduct apiProduct = restTemplate.getForObject(url, ApiProduct.class);
        return new ResponseEntity<>(apiProduct, HttpStatus.OK);
        //resposta do JSON com a minha api key
        //https://api.spoonacular.com/recipes/complexSearch?query=pasta&apiKey=b028691f707a4dd48a1222aeef34bd81
        //link site spoonacular para REQUEST
        //https://spoonacular.com/food-api/docs#Search-Recipes-Complex

        //ResponseEntity<String> asd = new ResponseEntity<>();
        //List<String> asdf = new ArrayList<>();
    }
}
