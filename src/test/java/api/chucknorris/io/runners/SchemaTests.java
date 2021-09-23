package api.chucknorris.io.runners;


import api.chucknorris.io.tests.categories.tests.CategoriasTest;
import api.chucknorris.io.tests.random.tests.RandomTest;
import api.chucknorris.io.tests.search.tests.SearchTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(api.chucknorris.io.suites.SchemaTests.class)
@Suite.SuiteClasses({
        RandomTest.class,
        SearchTest.class,
        CategoriasTest.class,
})

public class SchemaTests {
}
