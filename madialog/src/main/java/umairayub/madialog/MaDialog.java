package umairayub.madialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StyleRes;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MaDialog {

    public static class Builder {

        AlertDialog alertDialog;
        private MaDialogListener
                positiveButtonListener,
                negativeButtonListener;

        private String
                title,
                message,
                positiveButtonText,
                negativeButtonText;

        private int
                buttonTextColor,
                messageTextColor,
                titleTextColor,
                backgroundColor,
                image,
                gif,
                font_id,
                buttonOrientation;

        private List<Button> Buttons = new ArrayList<>();

        private boolean
                cancelOnOutsideTouch = true;

        private Context context;

        public Builder(Context context) {
            this.context = context;
        }


        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setCustomFont(int font_id) {
            this.font_id = font_id;
            return this;
        }

        public Builder setPositiveButtonListener(MaDialogListener positiveButtonListener) {
            this.positiveButtonListener = positiveButtonListener;
            return this;
        }

        public Builder setNegativeButtonListener(MaDialogListener negativeButtonListener) {
            this.negativeButtonListener = negativeButtonListener;
            return this;
        }

        public Builder setNegativeButtonText(String negativeButtonText) {
            this.negativeButtonText = negativeButtonText;
            return this;
        }

        public Builder setPositiveButtonText(String positiveButtonText) {
            this.positiveButtonText = positiveButtonText;
            return this;
        }

        public Builder setButtonTextColor(int buttonTextColor) {
            this.buttonTextColor = buttonTextColor;
            return this;
        }

        public Builder setCancelableOnOutsideTouch(boolean cancelOnOutsideTouch) {
            this.cancelOnOutsideTouch = cancelOnOutsideTouch;
            return this;
        }


        public Builder setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }


        public Builder setMessageTextColor(int messageTextColor) {
            this.messageTextColor = messageTextColor;
            return this;
        }

        public Builder setGif(int gif) {
            this.gif = gif;
            return this;
        }

        public Builder setImage(int image) {
            this.image = image;

            return this;
        }

        public Builder setButtonOrientation(int buttonOrientation) {
            this.buttonOrientation = buttonOrientation;
            return this;
        }

        public Builder setTitleTextColor(int titleTextColor) {
            this.titleTextColor = titleTextColor;
            return this;
        }

        public Builder AddNewButton(@StyleRes int style, String btnText, final MaDialogListener clickListener) {
            Button addbutton = new Button(new ContextThemeWrapper(context, style), null, style);
            LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            LayoutParams.setMargins(8, 8, 8, 8);
            addbutton.setLayoutParams(LayoutParams);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                addbutton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            addbutton.setText(btnText);
            addbutton.setTextSize(16);
            addbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick();
                    alertDialog.dismiss();

                }
            });
            Buttons.add(addbutton);
            return this;
        }

        public void build() {

            alertDialog = new AlertDialog.Builder(context).create();

            View view = LayoutInflater.from(context).inflate(R.layout.madialog, null);
            alertDialog.setView(view);
            if (alertDialog.getWindow() != null) {
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            alertDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            TextView tvTitle = (TextView) view.findViewById(R.id.tvTitleDisplay);
            TextView tvMessage = (TextView) view.findViewById(R.id.tvMessageDisplay);
            Button btnPositve = (Button) view.findViewById(R.id.btn_positive);
            Button btnNegative = (Button) view.findViewById(R.id.btn_negative);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            LinearLayout root = (LinearLayout) view.findViewById(R.id.rootly);
            LinearLayout ButtonContainer = view.findViewById(R.id.buttonLayout);

            if (buttonOrientation != 0) {
                ButtonContainer.setOrientation(buttonOrientation);
            }
            for (int i = 0; i < Buttons.size(); i++) {
                ButtonContainer.addView(Buttons.get(i));
            }


            if (message != null) {
                tvMessage.setVisibility(View.VISIBLE);
            }
            if (title != null) {
                tvTitle.setVisibility(View.VISIBLE);
            }
            tvMessage.setText(message);
            tvTitle.setText(title);
            if (image != 0) {
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageResource(image);
            }
            if (gif != 0) {
                imageView.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .asGif()
                        .load(gif)
                        .into(imageView);

            }
            if (font_id != 0) {
                Typeface tf = ResourcesCompat.getFont(context, font_id);
                tvTitle.setTypeface(tf);
                tvMessage.setTypeface(tf);
                btnPositve.setTypeface(tf);
                btnNegative.setTypeface(tf);
            }
            if (messageTextColor != 0) {
                tvMessage.setTextColor(messageTextColor);
            }
            if (titleTextColor != 0) {
                tvTitle.setTextColor(titleTextColor);
            }
            if (backgroundColor != 0) {
                root.setBackgroundResource(backgroundColor);
            }
            if (buttonTextColor != 0) {
                btnNegative.setTextColor(buttonTextColor);
                btnPositve.setTextColor(buttonTextColor);
            }
            if (negativeButtonText != null) {
                btnNegative.setText(negativeButtonText);
            }
            if (positiveButtonText != null) {
                btnPositve.setText(positiveButtonText);
            }

            if (negativeButtonListener != null) {
                btnNegative.setVisibility(View.VISIBLE);
                btnNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        negativeButtonListener.onClick();
                        alertDialog.dismiss();
                    }
                });
            } else {
                btnNegative.setVisibility(View.GONE);

            }
            if (positiveButtonListener != null) {
                btnPositve.setVisibility(View.VISIBLE);
                btnPositve.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        positiveButtonListener.onClick();
                        alertDialog.dismiss();
                    }
                });
            } else {
                btnPositve.setVisibility(View.GONE);

            }
            if (cancelOnOutsideTouch) {
                alertDialog.setCanceledOnTouchOutside(true);
            } else {
                alertDialog.setCanceledOnTouchOutside(false);

            }
            alertDialog.show();
        }
    }
}
