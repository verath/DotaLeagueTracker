/*
 * Copyright (C) 2017 The Android Open Source Project
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

package app.verath.dotaleaguetracker.model


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
@Suppress("DataClassPrivateConstructor")
data class Resource<out T>(val status: ResourceStatus, val data: T?, val msg: String?) {
    companion object {
        fun <T> loading(data: T?) = Resource(ResourceStatus.LOADING, data, null)
        fun <T> success(data: T?) = Resource(ResourceStatus.SUCCESS, data, null)
        fun <T> error(msg: String, data: T?) = Resource(ResourceStatus.ERROR, data, msg)
    }
}
