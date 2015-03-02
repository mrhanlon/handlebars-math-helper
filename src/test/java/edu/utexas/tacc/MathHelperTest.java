package edu.utexas.tacc;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * MathHelper Tests
 *
 */
public class MathHelperTest {

  protected Handlebars newHandlebars() {
    Handlebars handlebars = new Handlebars();
    handlebars.registerHelper("math", new MathHelper());
    return handlebars;
  }

  @Test
  public void addTest() throws IOException {
    shouldCompileTo("{{math this \"+\" 0}}", "2", "2");
    shouldCompileTo("{{math this \"+\" 2}}", "1", "3");
    shouldCompileTo("{{math this \"+\" \"4.5\"}}", "4.5", "9.0");
    shouldCompileTo("{{math this \"+\" \"4.5\"}}", "4", "8.5");
  }

  @Test
  public void subtractTest() throws IOException {
    shouldCompileTo("{{math this \"-\" 0}}", "2", "2");
    shouldCompileTo("{{math this \"-\" 2}}", "2", "0");
    shouldCompileTo("{{math this \"-\" 3}}", "2", "-1");
    shouldCompileTo("{{math this \"-\" \".5\"}}", "2", "1.5");
    shouldCompileTo("{{math this \"-\" \"-5\"}}", "2", "7");
  }

  @Test
  public void multiplyTest() throws IOException {
    shouldCompileTo("{{math this \"*\" 2}}", "1", "2");
    shouldCompileTo("{{math this \"*\" 2}}", "2", "4");
    shouldCompileTo("{{math this \"*\" 2}}", ".5", "1.0");
    shouldCompileTo("{{math this \"*\" \"2.5\"}}", "4", "10.0");
    shouldCompileTo("{{math this \"*\" \"2.5\" scale=0}}", "4", "10");
    shouldCompileTo("{{math this \"*\" \"100\"}}", "4", "400");
  }

  @Test
  public void divideTest() throws IOException {
    shouldCompileTo("{{math this \"/\" 2}}", "2", "1");
    shouldCompileTo("{{math this \"/\" 2}}", "1", "0.5");
    shouldCompileTo("{{math this \"/\" 3}}", "1", "0.3333333333333333");
    shouldCompileTo("{{math this \"/\" 3 scale=2}}", "1", "0.33");
    shouldCompileTo("{{math this \"/\" \".5\"}}", "2", "4");
    shouldCompileTo("{{math this \"/\" 1}}", "2", "2");
  }

  @Test
  public void modulusTest() throws IOException {
    shouldCompileTo("{{math this \"%\" 2}}", "3", "1");
    shouldCompileTo("{{math this \"%\" 2}}", "10.5", "0.5");
    shouldCompileTo("{{math this \"%\" 2 scale=0}}", "10.5", "1"); // 0.5 rounds up
    shouldCompileTo("{{math this \"%\" 2 scale=0}}", "10.25", "0"); // 0.25 rounds down
  }

  public void shouldCompileTo(String template, String augend, String expected) throws IOException {
    Handlebars hbs = new Handlebars();
    hbs.registerHelper("math", new MathHelper());

    Template t = hbs.compileInline(template);
    String result = t.apply(augend);

    Assert.assertEquals(expected, result);
  }
}
