package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun firstBun;
    @Mock
    private Bun replacementBun;
    @Mock
    private Ingredient firstIngredient;
    @Mock
    private Ingredient secondIngredient;
    @Mock
    private Ingredient thirdIngredient;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsStoresSelectedBun() {
        burger.setBuns(firstBun);

        assertSame(firstBun, burger.bun);
    }

    @Test
    public void setBunsReplacesPreviousBun() {
        burger.setBuns(firstBun);

        burger.setBuns(replacementBun);

        assertSame(replacementBun, burger.bun);
    }

    @Test
    public void addIngredientPreservesInsertionOrder() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        assertEquals(Arrays.asList(firstIngredient, secondIngredient), burger.ingredients);
    }

    @Test
    public void removeIngredientRemovesSelectedItemAndPreservesOthers() {
        addThreeIngredients();

        burger.removeIngredient(1);

        assertEquals(Arrays.asList(firstIngredient, thirdIngredient), burger.ingredients);
    }

    @Test
    public void moveIngredientMovesFirstItemToEnd() {
        addThreeIngredients();

        burger.moveIngredient(0, 2);

        assertEquals(Arrays.asList(secondIngredient, thirdIngredient, firstIngredient),
                burger.ingredients);
    }

    @Test
    public void moveIngredientMovesLastItemToBeginning() {
        addThreeIngredients();

        burger.moveIngredient(2, 0);

        assertEquals(Arrays.asList(thirdIngredient, firstIngredient, secondIngredient),
                burger.ingredients);
    }

    @Test
    public void moveIngredientToSameIndexPreservesOrder() {
        addThreeIngredients();

        burger.moveIngredient(1, 1);

        assertEquals(Arrays.asList(firstIngredient, secondIngredient, thirdIngredient),
                burger.ingredients);
    }

    private void addThreeIngredients() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);
    }
}
