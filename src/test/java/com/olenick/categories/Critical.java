package com.olenick.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

/**
 * Created by RShulman on 5/16/2016.
 */
@RunWith(Categories.class)
@Categories.IncludeCategory({Critical.class})
public interface Critical {
}
