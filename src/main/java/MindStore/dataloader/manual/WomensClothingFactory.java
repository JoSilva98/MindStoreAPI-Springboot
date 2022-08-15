package MindStore.dataloader.manual;

import MindStore.persistence.models.Person.User;
import MindStore.persistence.models.Product.AverageRating;
import MindStore.persistence.models.Product.Category;
import MindStore.persistence.models.Product.IndividualRating;
import MindStore.persistence.models.Product.Product;
import MindStore.persistence.repositories.Product.AverageRatingRepository;
import MindStore.persistence.repositories.Product.IndividualRatingRepository;

import java.util.ArrayList;
import java.util.List;

public class WomensClothingFactory {
    public static List<Product> womanClothingGenerator(List<User> users, Category womensClothing,
                                                       AverageRatingRepository avRatingRepository,
                                                       IndividualRatingRepository indRatingRepository) {
        //Rating 11
        List<Product> products = new ArrayList<>();
        int count = (int) (Math.random() * 8);
        List<IndividualRating> userRatings = new ArrayList<>();
        String title = "Ruffled silk dress";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        double average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating11 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        //Product 11
        Product product = Product.builder()
                .title(title)
                .price(3979.99)
                .description("Showcasing a '70s-inspired silhouette, this Gucci dress is crafted " +
                        "in a fluid silk fabrication. Decorated with ruffled trims, the romantic " +
                        "design is complete with GG buttons for a branded finish.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/55/11/10/18551110_39922671_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating11)
                .build();

        products.add(product);

        if (avRatingRepository.findByProductTitle(product.getTitle()).isEmpty()) {
            avRatingRepository.save(averageRating11);
            userRatings.forEach(x -> x.setAverageRatingId(averageRating11));
            indRatingRepository.saveAll(userRatings);
        }

        //-----------------------------
        //Rating 12
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Wide-leg GG jeans";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating12 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        //Product 12
        product = Product.builder()
                .title(title)
                .price(1149.99)
                .description("Gucci's signature GG pattern is prominent across all collections. " +
                        "It is also found on these wide-leg jeans which are presented in a light " +
                        "blue colourway with the notable design throughout.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/81/26/79/18812679_40800508_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating12)
                .build();

        products.add(product);

        if (avRatingRepository.findByProductTitle(product.getTitle()).isEmpty()) {
            avRatingRepository.save(averageRating12);
            userRatings.forEach(x -> x.setAverageRatingId(averageRating12));
            indRatingRepository.saveAll(userRatings);
        }

        //-----------------------------
        //Rating 13
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Graphic-print cotton sweatshirt";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating13 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        //Product 13
        product = Product.builder()
                .title(title)
                .price(819.99)
                .description("Graphic-print cotton sweatshirt from GUCCI featuring white, cotton, " +
                        "graphic print to the front, round neck, long sleeves and ribbed edge.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/74/01/77/18740177_40568518_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating13)
                .build();

        products.add(product);

        if (avRatingRepository.findByProductTitle(product.getTitle()).isEmpty()) {
            avRatingRepository.save(averageRating13);
            userRatings.forEach(x -> x.setAverageRatingId(averageRating13));
            indRatingRepository.saveAll(userRatings);
        }

        //-----------------------------
        //Rating 14
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Violet floral-print minidress";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating14 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        //Product 14
        product = Product.builder()
                .title(title)
                .price(674.99)
                .description("Cut for an A-line silhouette, ZIMMERMANN's Violet minidress is crafted" +
                        " from soft cotton and decorated with a colourful floral print all over. " +
                        "The piece is detailed with an elegant sweetheart neck and puff shoulders.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/17/94/07/63/17940763_40627267_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating14)
                .build();

        products.add(product);

        if (avRatingRepository.findByProductTitle(product.getTitle()).isEmpty()) {
            avRatingRepository.save(averageRating14);
            userRatings.forEach(x -> x.setAverageRatingId(averageRating14));
            indRatingRepository.saveAll(userRatings);
        }

        //-----------------------------
        //Rating 15
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Pleated two-tone sleeveless blouse";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating15 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        //Product 15
        product = Product.builder()
                .title(title)
                .price(889.99)
                .description("Marni's expertise in textiles dates back to the early '90s. The Italian " +
                        "house creates contemporary designs with sublime attention to detail. Such as " +
                        "this two-tone top, playful cream-hued pleats are juxtaposed with the navy fabric " +
                        "for a modern twist.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/44/98/88/18449888_39655759_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating15)
                .build();

        products.add(product);

        if (avRatingRepository.findByProductTitle(product.getTitle()).isEmpty()) {
            avRatingRepository.save(averageRating15);
            userRatings.forEach(x -> x.setAverageRatingId(averageRating15));
            indRatingRepository.saveAll(userRatings);
        }

        //-----------------------------
        //Rating 16
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "May plissé-effect short-sleeve shirt";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating16 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        //Product 16
        product = Product.builder()
                .title(title)
                .price(553.99)
                .description("Named after May, this Pleats Please Issey Miyake shirt is part of " +
                        "the label's Monthly Colour series, a project that is characterized by " +
                        "refreshing colours and billowing silhouettes inspired by the ocean. " +
                        "This iteration is presented in a pastel green colourway.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/17/89/18/47/17891847_40801966_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating16)
                .build();

        products.add(product);

        if (avRatingRepository.findByProductTitle(product.getTitle()).isEmpty()) {
            avRatingRepository.save(averageRating16);
            userRatings.forEach(x -> x.setAverageRatingId(averageRating16));
            indRatingRepository.saveAll(userRatings);
        }

        //-----------------------------
        //Rating 17
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Pleated full maxi skirt";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating17 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        //Product 17
        product = Product.builder()
                .title(title)
                .price(2989.99)
                .description("Never one to shy away from oversized proportions, Balenciaga present " +
                        "this full pleated maxi skirt for AW21. Crafted from silk, the A-line design " +
                        "sits high on the waist and fastens with a concealed side zipper closure.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/16/91/04/82/16910482_35698590_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating17)
                .build();

        products.add(product);

        if (avRatingRepository.findByProductTitle(product.getTitle()).isEmpty()) {
            avRatingRepository.save(averageRating17);
            userRatings.forEach(x -> x.setAverageRatingId(averageRating17));
            indRatingRepository.saveAll(userRatings);
        }

        //-----------------------------
        //Rating 18
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Feather-trim strapless dress";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating18 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        //Product 18
        product = Product.builder()
                .title(title)
                .price(514.99)
                .description("The upbeat and opulent design style of De La Vali is showcased " +
                        "on this strapless dress from the AW22 collection through the use " +
                        "satin-finish material and eye-catching feather-trim detailing.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/03/21/36/18032136_40641692_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating18)
                .build();

        products.add(product);

        if (avRatingRepository.findByProductTitle(product.getTitle()).isEmpty()) {
            avRatingRepository.save(averageRating18);
            userRatings.forEach(x -> x.setAverageRatingId(averageRating18));
            indRatingRepository.saveAll(userRatings);
        }

        //-----------------------------
        //Rating 19
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Pleated wide-leg trousers";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating19 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        //Product 19
        product = Product.builder()
                .title(title)
                .price(949.99)
                .description("Defined by its pleated detailing and knitted construction, these trousers " +
                        "by Stella McCartney follows the motto of the SS22 collection: returning to life. " +
                        "Crafted from sustainable viscose, it features an all-over metallic threading " +
                        "and a wide leg cut.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/17/96/19/45/17961945_38004049_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating19)
                .build();

        products.add(product);

        if (avRatingRepository.findByProductTitle(product.getTitle()).isEmpty()) {
            avRatingRepository.save(averageRating19);
            userRatings.forEach(x -> x.setAverageRatingId(averageRating19));
            indRatingRepository.saveAll(userRatings);
        }

        //-----------------------------
        //Rating 20
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Box-pleat mini skirt";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating20 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        //Product 20
        product = Product.builder()
                .title(title)
                .price(1983.99)
                .description("Prada is renowned for updating classic silhouettes with its signature " +
                        "branding. A fine demonstration is this pleated skirt. Boasting a virgin wool " +
                        "construction, the traditional style is elevated by the inclusion of the label's " +
                        "signature triangle logo to the belted waist, adding a recognisable and " +
                        "contemporary touch to the design.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/70/15/96/18701596_40633185_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating20)
                .build();

        products.add(product);

        if (avRatingRepository.findByProductTitle(product.getTitle()).isEmpty()) {
            avRatingRepository.save(averageRating20);
            userRatings.forEach(x -> x.setAverageRatingId(averageRating20));
            indRatingRepository.saveAll(userRatings);
        }

        return products;
    }
}
