/*
 * Copyright 2012 The Android Open Source Project
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
package com.sound.app;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import com.sound.app.auth.AuthActivity;

public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Intent intent = new Intent(this, AuthActivity.class);
        intent.putExtra(AuthActivity.TYPE_KEY, AuthActivity.Type.FOREGROUND.name());
        startActivity(intent);
        finish();
    }

}
