/*
 * Copyright 2017 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.anatomy.AnatomyCirculation;

import org.terasology.anatomy.AnatomyCirculation.component.InjuredCirculatoryComponent;
import org.terasology.anatomy.AnatomyCirculation.event.BloodLevelChangedEvent;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.entitySystem.prefab.PrefabManager;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.health.DestroyEvent;
import org.terasology.registry.In;

@RegisterSystem
public class CirculatoryEffectsSystem extends BaseComponentSystem {

    @In
    private PrefabManager prefabManager;

    @ReceiveEvent
    public void onBloodLevelChanged(BloodLevelChangedEvent event, EntityRef entityRef, InjuredCirculatoryComponent injuredCirculatoryComponent) {
        if (injuredCirculatoryComponent.bloodLevel <= 0) {
            Prefab bloodLossDamage = prefabManager.getPrefab("Anatomy:bloodLoss");
            entityRef.send(new DestroyEvent(EntityRef.NULL, EntityRef.NULL, bloodLossDamage));
        }
    }
}
