// $Id$
//
// (c) COPYRIGHT MIT, ECRIM and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.atrules.css3.media;

import org.w3c.css.atrules.css.media.MediaFeature;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssResolution;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2012/REC-css3-mediaqueries-20120619/#resolution
 */
public class MediaResolution extends MediaFeature {

    /**
     * Create a new MediaResolution
     */
    public MediaResolution() {
    }

    /**
     * Create a new MediaResolution
     *
     * @param expression The expression for this media feature
     * @throws org.w3c.css.util.InvalidParamException
     *          Values are incorrect
     */
    public MediaResolution(ApplContext ac, String modifier,
                           CssExpression expression, boolean check)
            throws InvalidParamException {

        if (expression != null) {
            if (expression.getCount() > 1) {
                throw new InvalidParamException("unrecognize", ac);
            }
            if (expression.getCount() == 0) {
                throw new InvalidParamException("few-value", getFeatureName(), ac);
            }
            CssValue val = expression.getValue();
            // it must be a >=0 integer only
            if (val.getType() == CssTypes.CSS_RESOLUTION) {
                CssResolution valnum = (CssResolution) val;

                if (valnum.getFloatValue() < 0.f) {
                    throw new InvalidParamException("negative-value",
                            val.toString(), ac);
                }
                value = valnum;
            } else {
                throw new InvalidParamException("unrecognize", ac);
            }
            setModifier(ac, modifier);
        } else {
            if (modifier != null) {
                throw new InvalidParamException("nomodifiershortmedia",
                        getFeatureName(), ac);
            }
        }
    }

    public MediaResolution(ApplContext ac, String modifier, CssExpression expression)
            throws InvalidParamException {
        this(ac, modifier, expression, false);
    }

    /**
     * Returns the value of this media feature.
     */

    public Object get() {
        return value;
    }

    /**
     * Returns the name of this media feature.
     */
    public final String getFeatureName() {
        return "resolution";
    }

    /**
     * Compares two media features for equality.
     *
     * @param other The other media features.
     */
    public boolean equals(MediaFeature other) {
        try {
            MediaResolution mr = (MediaResolution) other;
            return (((value == null) && (mr.value == null)) || ((value != null) && value.equals(mr.value)))
                    && (((modifier == null) && (mr.modifier == null)) || ((modifier != null) && modifier.equals(mr.modifier)));
        } catch (ClassCastException cce) {
            return false;
        }

    }
}