package application;


import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */

@XmlRegistry
public class ObjectFactory {
    private final static QName _RecipeRemarkQuote_QNAME = new QName("", "quote");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Recipe }
     * 
     */
    public Recipe createRecipe() {
        return new Recipe();
    }

    /**
     * Create an instance of {@link Recipe.Ingredients }
     * 
     */
    public Recipe.Ingredients createRecipeIngredients() {
        return new Recipe.Ingredients();
    }

    /**
     * Create an instance of {@link Recipe.Remark }
     * 
     */
    public Recipe.Remark createRecipeRemark() {
        return new Recipe.Remark();
    }

    /**
     * Create an instance of {@link Recipe.Duration }
     * 
     */
    public Recipe.Duration createRecipeDuration() {
        return new Recipe.Duration();
    }

    /**
     * Create an instance of {@link Recipe.Ingredients.Ingredient }
     * 
     */
    public Recipe.Ingredients.Ingredient createRecipeIngredientsIngredient() {
        return new Recipe.Ingredients.Ingredient();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "quote", scope = Recipe.Remark.class)
    public JAXBElement<String> createRecipeRemarkQuote(String value) {
        return new JAXBElement<String>(_RecipeRemarkQuote_QNAME, String.class, Recipe.Remark.class, value);
    }

	

}
