package com.mmm.grenway.javafx.controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javafx.scene.control.TextField;

public abstract class BaseController {
	protected ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	protected ScheduledFuture<?> result = null;
	
	protected void initFiltersListeners() {
		getClientNameFilter().textProperty().addListener((ob, ov, nv) -> {
			if (result != null) {
				result.cancel(false);
			}
			result = scheduler.schedule(new Runnable() {

				@Override
				public void run() {
					refreshTable();
				}
			}, 1, TimeUnit.SECONDS);

		});

		getClientPhoneFilter().textProperty().addListener((ob, ov, nv) -> {
			if (result != null) {
				result.cancel(false);
			}
			result = scheduler.schedule(new Runnable() {

				@Override
				public void run() {
					refreshTable();
				}
			}, 1, TimeUnit.SECONDS);
		});
	}
	
	protected abstract TextField getClientNameFilter();
	protected abstract TextField getClientPhoneFilter();
	
	protected abstract void refreshTable();
	
}
