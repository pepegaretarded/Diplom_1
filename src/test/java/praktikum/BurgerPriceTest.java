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
public class BurgerPriceTest {

    private final float bunPrice;
    private final float[] ingredientPrices;
    private final float expectedPrice;

    public BurgerPriceTest(String scenario, float bunPrice, float[] ingredientPrices,
                           float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object[]> priceCases() {
        return Arrays.asList(new Object[][] {
                {"two bun halves without ingredients", 100f, new float[] {}, 200f},
                {"one ingredient", 100f, new float[] {25f}, 225f},
                {"multiple ingredients with fractional prices", 100.25f,
                        new float[] {10.5f, 74.5f}, 285.5f},
                {"zero price", 0f, new float[] {0f}, 0f}
        });
    }

    @Test
    public void getPriceIncludesTwoBunHalvesAndEveryIngredient() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);
        for (float ingredientPrice : ingredientPrices) {
            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(ingredientPrice);
            burger.addIngredient(ingredient);
        }

        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }
}
