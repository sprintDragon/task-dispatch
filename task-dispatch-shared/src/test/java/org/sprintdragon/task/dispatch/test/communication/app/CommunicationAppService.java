/*
 * Copyright (C) 2010-2101 Alibaba Group Holding Limited.
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

package org.sprintdragon.task.dispatch.test.communication.app;


import org.sprintdragon.task.dispatch.shared.communication.core.model.Event;
import org.sprintdragon.task.dispatch.test.communication.app.event.AppCreateEvent;
import org.sprintdragon.task.dispatch.test.communication.app.event.AppDeleteEvent;
import org.sprintdragon.task.dispatch.test.communication.app.event.AppUpdateEvent;

/**
 * @author jianghang 2011-9-13 下午08:30:48
 */
public interface CommunicationAppService {

    public boolean onCreate(AppCreateEvent event);

    public boolean onUpdate(AppUpdateEvent event);

    public boolean onDelete(AppDeleteEvent event);

    public Event handleEvent(Event event);
}
