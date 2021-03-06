/**
 *
 * Copyright (c) 2006-2017, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.runtime.field.internal.predicate.string;

import com.speedment.runtime.field.predicate.FieldPredicate;
import com.speedment.runtime.field.predicate.PredicateType;
import com.speedment.runtime.field.trait.HasReferenceValue;

/**
 *
 * @param <ENTITY> the entity type
 * @param <D> the database type
 *
 * @author Per Minborg
 * @since 2.2.0
 */
public final class StringContainsPredicate<ENTITY, D> extends AbstractStringPredicate<ENTITY, D> {

    public StringContainsPredicate(HasReferenceValue<ENTITY, D, String> field, String str) {
        this(field, str, false);
    }
    
    StringContainsPredicate(HasReferenceValue<ENTITY, D, String> field, String str, boolean negated) {
        super(PredicateType.CONTAINS, field, str, entity -> {
            final String fieldValue = field.get(entity);
            return fieldValue != null
                && str != null
                && fieldValue.contains(str);
        }, negated);
    }

    @Override
    public StringContainsPredicate<ENTITY, D> negate() {
        return new StringContainsPredicate<>(getField(), get0(), !isNegated());
    }
    
}
