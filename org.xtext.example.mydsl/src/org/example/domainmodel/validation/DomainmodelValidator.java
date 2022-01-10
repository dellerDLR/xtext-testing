/*
 * generated by Xtext 2.25.0
 */
package org.example.domainmodel.validation;

import org.eclipse.xtext.validation.Check;
import org.example.domainmodel.domainmodel.DomainmodelPackage;
import org.example.domainmodel.domainmodel.Entity;
import org.example.domainmodel.domainmodel.Feature;

import com.google.common.base.Objects;

/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
public class DomainmodelValidator extends AbstractDomainmodelValidator {
	
	public static final String INVALID_NAME = "invalidName";

	@Check
	public void checkNameStartsWithCapital(Entity entity) {
	    if (!Character.isUpperCase(entity.getName().charAt(0))) {
	        warning("Name should start with a capital",
	            DomainmodelPackage.Literals.TYPE__NAME,
	            INVALID_NAME);
	    }
	}
	
    @Check
    public void checkFeatureNameIsUnique(Feature feature) {
        Entity superEntity = ((Entity) feature.eContainer()).getSuperType();
        while (superEntity != null) {
            for (Feature other : superEntity.getFeatures()) {
                if (Objects.equal(feature.getName(), other.getName())) {
                    error("Feature names have to be unique", DomainmodelPackage.Literals.FEATURE__NAME);
                    return;
                }
            }
            superEntity = superEntity.getSuperType();
        }
    }
	
}
