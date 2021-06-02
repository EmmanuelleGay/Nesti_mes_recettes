package entity;

public class Recipe {

    String title;
    String cat;
    String author;
    int imgId;
    int rating;
    String idRecipe;
    String imagePath;

    Object Ingredient;
    Object Step;


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(String id) {
        this.idRecipe = id;
    }

    public Object getIngredient() {
        return Ingredient;
    }

    public void setIngredient(Object ingredient) {
        Ingredient = ingredient;
    }

    public Object getStep() {
        return Step;
    }

    public void setStep(Object step) {
        Step = step;
    }


    @Override
    public String toString(){
        return "Recipe{" +
                "cat : " + cat +
                ", title : " + title +
                ", imgId : " + imgId +
                ", author : }" + author;
    }
}
