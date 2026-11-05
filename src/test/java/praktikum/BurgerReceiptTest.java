package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptTest {

    private final IngredientType[] ingredientTypes;
    private final String[] ingredientNames;
    private final float[] ingredientPrices;
    private final String expectedReceipt;

    public BurgerReceiptTest(String scenario, IngredientType[] ingredientTypes,
                             String[] ingredientNames, float[] ingredientPrices,
                             String expectedReceipt) {
        this.ingredientTypes = ingredientTypes;
        this.ingredientNames = ingredientNames;
        this.ingredientPrices = ingredientPrices;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object[]> receiptCases() {
        return Arrays.asList(new Object[][] {
                {"without ingredients", new IngredientType[] {}, new String[] {},
                        new float[] {},
                        String.format("(==== Space bun ====)%n(==== Space bun ====)%n%nPrice: %f%n", 200f)},
                {"sauce", new IngredientType[] {IngredientType.SAUCE},
                        new String[] {"Hot sauce"}, new float[] {10.5f},
                        String.format("(==== Space bun ====)%n= sauce Hot sauce =%n"
                                + "(==== Space bun ====)%n%nPrice: %f%n", 210.5f)},
                {"filling", new IngredientType[] {IngredientType.FILLING},
                        new String[] {"Meteor cutlet"}, new float[] {75f},
                        String.format("(==== Space bun ====)%n= filling Meteor cutlet =%n"
                                + "(==== Space bun ====)%n%nPrice: %f%n", 275f)},
                {"mixed ingredients in insertion order",
                        new IngredientType[] {IngredientType.FILLING, IngredientType.SAUCE},
                        new String[] {"Meteor cutlet", "Hot sauce"}, new float[] {75f, 10.5f},
                        String.format("(==== Space bun ====)%n= filling Meteor cutlet =%n"
                                + "= sauce Hot sauce =%n(==== Space bun ====)%n%nPrice: %f%n", 285.5f)}
        });
    }

    @Test
    public void getReceiptShowsBunsIngredientTypesNamesOrderAndTotal() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("Space bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);
        for (int i = 0; i < ingredientTypes.length; i++) {
            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getType()).thenReturn(ingredientTypes[i]);
            when(ingredient.getName()).thenReturn(ingredientNames[i]);
            when(ingredient.getPrice()).thenReturn(ingredientPrices[i]);
            burger.addIngredient(ingredient);
        }

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
