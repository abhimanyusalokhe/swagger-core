package io.swagger;

import io.swagger.converter.ModelConverters;
import io.swagger.models.ModelWithRanges;
import io.swagger.models.properties.DoubleProperty;
import io.swagger.models.properties.IntegerProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.StringProperty;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class ModelWithRangesTest {

    @Test(description = "test model with @ApiModelProperty.allowableValues")
    public void modelWithRangesTest() {
        final Map<String, Property> properties = ModelConverters.getInstance().read(ModelWithRanges.class).get("ModelWithRanges").getProperties();

        final IntegerProperty inclusiveRange = (IntegerProperty) properties.get("inclusiveRange");
        assertEquals(inclusiveRange.getMinimum(), 1.0);
        assertEquals(inclusiveRange.getMaximum(), 5.0);
        assertNull(inclusiveRange.getExclusiveMaximum());
        assertNull(inclusiveRange.getExclusiveMinimum());

        final IntegerProperty exclusiveRange = (IntegerProperty) properties.get("exclusiveRange");
        assertEquals(exclusiveRange.getMinimum(), 1.0);
        assertEquals(exclusiveRange.getMaximum(), 5.0);
        assertEquals(exclusiveRange.getExclusiveMinimum(), Boolean.TRUE);
        assertEquals(exclusiveRange.getExclusiveMaximum(), Boolean.TRUE);

        final IntegerProperty positiveInfinityRange = (IntegerProperty) properties.get("positiveInfinityRange");
        assertEquals(positiveInfinityRange.getMinimum(), 1.0);
        assertNull(positiveInfinityRange.getMaximum());
        assertNull(positiveInfinityRange.getExclusiveMaximum());
        assertNull(positiveInfinityRange.getExclusiveMinimum());

        final IntegerProperty negativeInfinityRange = (IntegerProperty) properties.get("negativeInfinityRange");
        assertNull(negativeInfinityRange.getMinimum());
        assertEquals(negativeInfinityRange.getMaximum(), 5.0);
        assertNull(negativeInfinityRange.getExclusiveMaximum());
        assertNull(negativeInfinityRange.getExclusiveMinimum());

        final StringProperty stringValues = (StringProperty) properties.get("stringValues");
        assertEquals(stringValues.getEnum(), Arrays.asList("str1", "str2"));

        final DoubleProperty doubleValues = (DoubleProperty) properties.get("doubleValues");
        assertEquals(doubleValues.getMinimum(), 1.0);
        assertEquals(doubleValues.getMaximum(), 8.0);
        assertEquals(doubleValues.getExclusiveMaximum(), Boolean.TRUE);
        assertNull(doubleValues.getExclusiveMinimum());
    }
}
