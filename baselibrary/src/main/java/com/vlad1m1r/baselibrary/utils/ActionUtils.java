/*
 * Copyright 2017 Vladimir Jovanovic
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

package com.vlad1m1r.baselibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Patterns;

import com.vlad1m1r.baselibrary.R;

import static com.google.common.base.Preconditions.checkNotNull;


public class ActionUtils {

    public enum ActionType {
        WEB,
        EMAIL,
        CALL,
        SHARE,
        EXTERNAL,
        PLAY_STORE,
        VIBER
    }

    public static void doAction(final Context context, String data) {
        checkNotNull(context);
        checkNotNull(data);

        if (data.startsWith("mailto:")) {
            data = data.replaceAll("mailto:", "");

            if (Patterns.EMAIL_ADDRESS.matcher(data).matches()) {
                doAction(context, ActionType.EMAIL, data);
            }

        } else if (Patterns.EMAIL_ADDRESS.matcher(data).matches()) {
            doAction(context, ActionType.EMAIL, data);
        } else if (Patterns.WEB_URL.matcher(data).matches()) {
            doAction(context, ActionType.WEB, data);
        } else if (Patterns.PHONE.matcher(data).matches()) {
            doAction(context, ActionType.CALL, data);
        }
    }

    public static void doAction(Context context, ActionType type, String data) {
        checkNotNull(context);
        checkNotNull(type);
        checkNotNull(data);

        switch (type) {
            case EMAIL: { //email
                if (Patterns.EMAIL_ADDRESS.matcher(data).matches()
                        || (data.startsWith("mailto:") && Patterns.EMAIL_ADDRESS.matcher(data.substring(7)).matches())) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{data});
                    context.startActivity(Intent.createChooser(intent, ""));
                }
                break;
            }
            case CALL: { //phone
                if (Patterns.PHONE.matcher(data).matches()) {
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:" + data));
                    context.startActivity(Intent.createChooser(i, context.getResources().getString(R.string.action__call_number)));
                }
                break;
            }
            case SHARE: {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, data);
                context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.action__share_via)));
                break;
            }
            case EXTERNAL: {
                if (data.startsWith("www")) {
                    data = "http://" + data;
                }
                if (Patterns.WEB_URL.matcher(data).matches()) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
                    context.startActivity(browserIntent);
                }
                break;
            }
            case PLAY_STORE: {
                if (data.startsWith("market:")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
                    context.startActivity(browserIntent);
                }
                break;
            }
            case VIBER: {
                if (Patterns.PHONE.matcher(data).matches()) {
                    Uri uri = Uri.parse("tel:" + Uri.encode(data));
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setClassName("com.viber.voip", "com.viber.voip.WelcomeActivity");
                    intent.setData(uri);
                    context.startActivity(intent);
                }
                break;
            }
            default: {
                throw new IllegalArgumentException("Used unsupported argument " + type.name());
            }
        }
    }

    public static void shareDataFromApp(final Context context, String data, String subject) {
        checkNotNull(context);
        checkNotNull(data);

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, data);
        if(subject != null) sharingIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.action__share_via)));
    }
}
