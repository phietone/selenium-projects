package com.olenick.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import static org.junit.experimental.categories.Categories.*;

/**
 * Created by RShulman on 5/16/2016.
 */
@RunWith(Categories.class)
@IncludeCategory({Major.class})
public interface Major {
}
