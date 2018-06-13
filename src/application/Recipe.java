/**
 * @author Saadman Haq s160081
 */

package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="startdate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="changedate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="target" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ingredients">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ingredient" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="iname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="preparation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="variation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="remark">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="quote" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="privateremark" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="duration">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="totaltime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="worktime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "title",
    "id",
    "startdate",
    "changedate",
    "target",
    "ingredients",
    "tasks",
    "preparation",
    "variation",
    "remark",
    "privateremark",
    "rating",
    "calories",
    "fat",
    "carbohydrates",
    "protein",
    "duration",
    "source"
})
@XmlRootElement(name = "recipe")
public class Recipe {

    @XmlElement(required = true)
    protected String title;
    @XmlElement(name = "ID")
    protected byte id;
    @XmlElement(required = true)
    protected String startdate;
    @XmlElement(required = true)
    protected String changedate;
    @XmlElement(required = true)
    protected String target;
    @XmlElement(required = true)
    protected Recipe.Ingredients ingredients;
    @XmlElement(required = true)
    protected Recipe.Tasks tasks;
    @XmlElement(required = true)
    protected String preparation;
    @XmlElement(required = true)
    protected String variation;
    @XmlElement(required = true)
    protected Recipe.Remark remark;
    @XmlElement(required = true)
    protected String privateremark;
    @XmlElement (required = true)
    protected int rating;
    @XmlElement(required = true)
    protected Recipe.Duration duration;
    @XmlElement(required = true)
    protected String source;
    @XmlElement(required = true)
    protected String calories;
    @XmlElement(required = true)
    protected float fat;
    @XmlElement(required = true)
    protected float carbohydrates;
    @XmlElement(required = true)
    protected float protein;
    

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public byte getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setID(byte value) {
        this.id = value;
    }

    /**
     * Gets the value of the startdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * Sets the value of the startdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartdate(String value) {
        this.startdate = value;
    }

    /**
     * Gets the value of the changedate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangedate() {
        return changedate;
    }

    /**
     * Sets the value of the changedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangedate(String value) {
        this.changedate = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }

    /**
     * Gets the value of the ingredients property.
     * 
     * @return
     *     possible object is
     *     {@link Recipe.Ingredients }
     *     
     */
    public Recipe.Ingredients getIngredients() {
        return ingredients;
    }

    /**
     * Sets the value of the ingredients property.
     * 
     * @param value
     *     allowed object is
     *     {@link Recipe.Ingredients }
     *     
     */
    public void setIngredients(Recipe.Ingredients value) {
        this.ingredients = value;
    }
    
    public Recipe.Tasks getTasks() {
    	return tasks;
    }
    
    public void setTasks(Recipe.Tasks tasks) {
    	this.tasks = tasks;
    }

    /**
     * Gets the value of the preparation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreparation() {
        return preparation;
    }

    /**
     * Sets the value of the preparation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreparation(String value) {
        this.preparation = value;
    }

    /**
     * Gets the value of the variation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariation() {
        return variation;
    }

    /**
     * Sets the value of the variation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariation(String value) {
        this.variation = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link Recipe.Remark }
     *     
     */
    public Recipe.Remark getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link Recipe.Remark }
     *     
     */
    public void setRemark(Recipe.Remark value) {
        this.remark = value;
    }

    /**
     * Gets the value of the privateremark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivateremark() {
        return privateremark;
    }

    /**
     * Sets the value of the privateremark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivateremark(String value) {
        this.privateremark = value;
    }
    
    public int getRating() {
    	return rating;
    }
    
    public void setRating(int rating) {
    	this.rating = rating;
    }
    
    public String getCalories() {
    	return calories;
    }
    
    public void setCalories(String calories) {
    	this.calories = calories;
    }
    
    public float getFat() {
    	return fat;
    }
    
    public void setFat(float fat) {
    	this.fat = fat;
    }
    
    public float getCarbohydrates() {
    	return carbohydrates;
    }
    
    public void setCarbohydrates(float carbohydrates) {
    	this.carbohydrates = carbohydrates;
    }
    
    public float getProtein() {
    	return protein;
    }
    
    public void setProtein(float protein) {
    	this.protein = protein;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link Recipe.Duration }
     *     
     */
    public Recipe.Duration getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Recipe.Duration }
     *     
     */
    public void setDuration(Recipe.Duration value) {
        this.duration = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="totaltime" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="worktime" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "totaltime",
        "worktime"
    })
    public static class Duration {

        @XmlElement(required = true)
        protected String totaltime;
        @XmlElement(required = true)
        protected String worktime;

        /**
         * Gets the value of the totaltime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTotaltime() {
            return totaltime;
        }

        /**
         * Sets the value of the totaltime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTotaltime(String value) {
            this.totaltime = value;
        }

        /**
         * Gets the value of the worktime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getWorktime() {
            return worktime;
        }

        /**
         * Sets the value of the worktime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWorktime(String value) {
            this.worktime = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ingredient" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="iname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "ingredient"
    })
    public static class Ingredients {

        protected List<Recipe.Ingredients.Ingredient> ingredient;

        /**
         * Gets the value of the ingredient property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the ingredient property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIngredient().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Recipe.Ingredients.Ingredient }
         * 
         * 
         */
        public List<Recipe.Ingredients.Ingredient> getIngredient() {
            if (ingredient == null) {
                ingredient = new ArrayList<Recipe.Ingredients.Ingredient>();
            }
            return this.ingredient;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="iname" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "quantity",
            "unit",
            "iname"
        })
        public static class Ingredient {

            @XmlElement(required = true)
            protected String quantity;
            @XmlElement(required = true)
            protected String unit;
            @XmlElement(required = true)
            protected String iname;

            /**
             * Gets the value of the quantity property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getQuantity() {
                return quantity;
            }

            /**
             * Sets the value of the quantity property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setQuantity(String value) {
                this.quantity = value;
            }

            /**
             * Gets the value of the unit property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUnit() {
                return unit;
            }

            /**
             * Sets the value of the unit property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUnit(String value) {
                this.unit = value;
            }

            /**
             * Gets the value of the iname property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIname() {
                return iname;
            }

            /**
             * Sets the value of the iname property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIname(String value) {
                this.iname = value;
            }

        }

    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "task"
    })
    
    public static class Tasks {
    	protected List<Recipe.Tasks.Task> task;
    	
    	public List<Recipe.Tasks.Task> getTask() {
            if (task == null) {
                task = new ArrayList<Recipe.Tasks.Task>();
            }
            return this.task;
        }
    	
    	@XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "ID",
            "parents",
            "children",
            "time",
            "attentionRequired",
            "taskString",
            "timerString",
            "alertString",
            "taskTitle"
        })
    	public static class Task {
        	
    		@XmlElement(required = true)
            protected int ID;
            @XmlElement(required = true)
            protected String[] parents;
            @XmlElement(required = true)
            protected String[] children;
            @XmlElement(required = true)
            protected int time;
            @XmlElement(required = true)
            protected boolean attentionRequired;
            @XmlElement(required = true)
            protected String taskString;
            @XmlElement(required = true)
            protected String timerString;
            @XmlElement(required = true)
            protected String alertString;
            @XmlElement(required = true)
            protected String taskTitle;
            
            public int getID() {
            	return ID;
            }
            
            public void setID(int ID) {
            	this.ID = ID;
            }
            
            public String[] getParents() {
            	return parents;
            }
            
            public void setParents(String[] parents) {
            	this.parents = parents;
            }
            
            public String[] getChildren() {
            	return children;
            }
            
            public void setChildren(String[] children) {
            	this.children = children;
            }
            
            public int getTime() {
            	return time;
            }
            
            public void setTime(int time) {
            	this.time = time;
            }
            
            public boolean getAttentionRequired() {
            	return attentionRequired;
            }
            
            public void setAttentionRequired(boolean attentionRequired) {
            	this.attentionRequired = attentionRequired;
            }
            
            public String getTaskString() {
            	return taskString;
            }
            
            public void setTaskString(String taskString) {
            	this.taskString = taskString;
            }
            
            public String getTimerString() {
            	return timerString;
            }
            
            public void setTimerString(String timerString) {
            	this.timerString = timerString;
            }
            
            public String getAlertString() {
            	return alertString;
            }
            
            public void setAlertString(String alertString) {
            	this.alertString = alertString;
            }
            
            public String getTaskTitle() {
            	return taskTitle;
            }
            
            public void setTaskTitle(String taskTitle) {
            	this.taskTitle = taskTitle;
            }
        }
    }

    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="quote" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "content"
    })
    public static class Remark {

        @XmlElementRef(name = "quote", type = JAXBElement.class)
        @XmlMixed
        protected List<Serializable> content;

        /**
         * Gets the value of the content property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the content property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * 
         * 
         */
        public List<Serializable> getContent() {
            if (content == null) {
                content = new ArrayList<Serializable>();
            }
            return this.content;
        }

    }

}