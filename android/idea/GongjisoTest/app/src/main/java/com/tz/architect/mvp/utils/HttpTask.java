package com.tz.architect.mvp.utils;

import android.os.AsyncTask;

import com.tz.architect.mvp.utils.HttpUtils.OnHttpResultListener;

public class HttpTask extends AsyncTask<String, Void, String> {

	private OnHttpResultListener onHttpResultListener;

	public HttpTask(OnHttpResultListener onHttpResultListener) {
		this.onHttpResultListener = onHttpResultListener;
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			return HttpUtils.post(params[0], params[1], params[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		if (this.onHttpResultListener != null) {
			this.onHttpResultListener.onResult(result);
		}
	}

}
