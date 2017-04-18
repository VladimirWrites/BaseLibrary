package com.vlad1m1r.baselibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Patterns;

import com.vlad1m1r.baselibrary.R;


/**
 * Created by vladimirjovanovic on 10/21/15.
 */
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

    public static void doAction(@NonNull final Context context, @NonNull String data) {
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
        switch (type) {
            case WEB: {
                if (data.startsWith("www")) {
                    data = "http://" + data;
                }
                if (Patterns.WEB_URL.matcher(data).matches()) {

//                    CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
//
//                    intentBuilder.setToolbarColor(context.getResources().getColor(R.color.color_primary));
//                    intentBuilder.setShowTitle(true);
//                    intentBuilder.setStartAnimations(context,
//                            R.anim.push_left_in, R.anim.fadeout);
//                    intentBuilder.setExitAnimations(context,
//                            R.anim.fadein, R.anim.push_right_out);
//
//
//                    Bitmap mCloseButtonBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_navigation_arrow_back);
//                    intentBuilder.setCloseButtonIcon(mCloseButtonBitmap);
//
//                    Uri uri = Uri.parse(mainData);
//
//                    CustomTabActivityHelper.openCustomTab(context, intentBuilder.build(), uri, null);

                }
                break;
            }
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

            }
        }
    }
}
