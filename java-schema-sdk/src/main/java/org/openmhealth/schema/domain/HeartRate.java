/*
 * Copyright 2015 Open mHealth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openmhealth.schema.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.openmhealth.schema.serializer.SerializationConstructor;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.openmhealth.schema.domain.HeartRateUnit.BEATS_PER_MINUTE;


/**
 * A person's heart rate.
 *
 * @author Emerson Farrugia
 * @version 1.0
 * @see <a href="http://www.openmhealth.org/developers/schemas/#heart-rate">heart-rate</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
public class HeartRate extends Measure {

    private TypedUnitValue<HeartRateUnit> heartRate;
    private TemporalRelationshipToPhysicalActivity temporalRelationshipToPhysicalActivity;


    @SerializationConstructor
    private HeartRate() {
    }

    public static class Builder extends Measure.Builder<Builder> {

        private TypedUnitValue<HeartRateUnit> heartRate;
        private TemporalRelationshipToPhysicalActivity temporalRelationshipToPhysicalActivity;

        public Builder(BigDecimal heartRateValue) {

            checkNotNull(heartRateValue, "A heart rate hasn't been specified.");

            this.heartRate = new TypedUnitValue<>(BEATS_PER_MINUTE, heartRateValue);
        }

        public Builder setTemporalRelationshipToPhysicalActivity(TemporalRelationshipToPhysicalActivity relationship) {
            this.temporalRelationshipToPhysicalActivity = relationship;
            return this;
        }

        @Override
        public HeartRate build() {
            return new HeartRate(this);
        }
    }

    private HeartRate(Builder builder) {
        super(builder);

        this.heartRate = builder.heartRate;
        this.temporalRelationshipToPhysicalActivity = builder.temporalRelationshipToPhysicalActivity;
    }

    public TypedUnitValue<HeartRateUnit> getHeartRate() {
        return heartRate;
    }

    public TemporalRelationshipToPhysicalActivity getTemporalRelationshipToPhysicalActivity() {
        return temporalRelationshipToPhysicalActivity;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }

        HeartRate heartRate1 = (HeartRate) object;

        if (!heartRate.equals(heartRate1.heartRate)) {
            return false;
        }
        return temporalRelationshipToPhysicalActivity == heartRate1.temporalRelationshipToPhysicalActivity;

    }

    @Override
    public int hashCode() {

        int result = super.hashCode();
        result = 31 * result + heartRate.hashCode();
        result = 31 * result +
                (temporalRelationshipToPhysicalActivity != null ? temporalRelationshipToPhysicalActivity.hashCode()
                        : 0);
        return result;
    }
}
