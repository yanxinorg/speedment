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
package com.speedment.runtime.field.internal.predicate.reference;

import com.speedment.common.tuple.Tuple1;
import com.speedment.runtime.field.internal.predicate.AbstractFieldPredicate;
import static com.speedment.runtime.field.predicate.PredicateType.EQUAL;
import com.speedment.runtime.field.trait.HasReferenceValue;
import java.util.Objects;

/**
 *
 * @param <ENTITY>  the entity type
 * @param <D>       the database type
 * @param <V>       the value type
 * 
 * @author  Per Minborg
 * @since   2.2.0
 */
public final class ReferenceEqualPredicate<ENTITY, D, V extends Comparable<? super V>>
        extends AbstractFieldPredicate<ENTITY, V, HasReferenceValue<ENTITY, D, V>>
        implements Tuple1<V> {

    private final V value;
    
    public ReferenceEqualPredicate(HasReferenceValue<ENTITY, D, V> field, V value) {
        this(field, value, false);
    }
    
    ReferenceEqualPredicate(HasReferenceValue<ENTITY, D, V> field, V value, boolean negated) {
        super(EQUAL, field, entity -> Objects.equals(field.get(entity), value), negated);
        this.value = value;
    }

    @Override
    public V get0() {
        return value;
    }

    @Override
    public ReferenceEqualPredicate<ENTITY, D, V> negate() {
        return new ReferenceEqualPredicate<>(getField(), value, !isNegated());
    }
     
}