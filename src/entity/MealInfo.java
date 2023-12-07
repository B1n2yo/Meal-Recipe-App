package entity;

public class MealInfo implements Meal {
    private String name;
    private String description;
    private float calories;
    private float protein;

    public MealInfo(String name, String description, float calories, float protein) {
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.protein = protein;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public float getCalories() {
        return calories;
    }

    @Override
    public float getProtein() {
        return protein;
    }
}
