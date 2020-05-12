package sdk.chat.message.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.stfalcon.chatkit.messages.MessageHolders;

import sdk.chat.core.dao.Keys;
import sdk.chat.core.dao.Message;
import sdk.chat.core.handlers.MessageHandler;
import sdk.chat.core.module.AbstractModule;
import sdk.chat.core.module.Module;
import sdk.chat.core.session.ChatSDK;
import sdk.chat.core.types.MessageType;
import co.chatsdk.message.video.R;
import co.chatsdk.ui.chat.model.MessageHolder;
import co.chatsdk.ui.chat.options.MediaChatOption;
import co.chatsdk.ui.chat.options.MediaType;
import co.chatsdk.ui.custom.Customiser;
import co.chatsdk.ui.custom.IMessageHandler;

/**
 * Created by ben on 10/6/17.
 */

public class VideoMessageModule extends AbstractModule {

    public static final VideoMessageModule instance = new VideoMessageModule();

    public static VideoMessageModule shared() {
        return instance;
    }

    @Override
    public void activate(Context context) {
        ChatSDK.a().videoMessage = new BaseVideoMessageHandler();
        ChatSDK.ui().addChatOption(new MediaChatOption(context.getResources().getString(R.string.take_video), MediaType.takeVideo()));
        ChatSDK.ui().addChatOption(new MediaChatOption(context.getResources().getString(R.string.choose_video), MediaType.chooseVideo()));

        Customiser.shared().addMessageHandler(new IMessageHandler() {
            @Override
            public boolean hasContentFor(MessageHolder message, byte type) {
                return type == MessageType.Video && message instanceof VideoMessageHolder;
            }

            @Override
            public void onBindMessageHolders(Context ctx, MessageHolders holders) {
                holders.registerContentType(
                        (byte) MessageType.Video,
                        IncomingVideoMessageViewHolder.class,
                        R.layout.view_holder_incoming_video_message,
                        OutcomingVideoMessageViewHolder.class,
                        R.layout.view_holder_outcoming_video_message, this);
            }

            @Override
            public MessageHolder onNewMessageHolder(Message message) {
                if (message.getMessageType().is(MessageType.Video)) {
                    return new VideoMessageHolder(message);
                }
                return null;
            }

            @Override
            public void onClick(Activity activity, View rootView, Message message) {
                if (message.getMessageType().is(MessageType.Video)) {
                    String videoURL = (String) message.valueForKey(Keys.MessageVideoURL);
                    if(videoURL != null) {
                        Intent intent = new Intent(activity, PlayVideoActivity.class);
                        intent.putExtra(Keys.IntentKeyFilePath, videoURL);
                        activity.startActivity(intent);
                    }
                }
            }

            @Override
            public void onLongClick(Activity activity, View rootView, Message message) {

            }
        });    }

    @Override
    public String getName() {
        return "VideoMessagesModule";
    }

    @Override
    public MessageHandler getMessageHandler() {
        return ChatSDK.videoMessage();
    }

}