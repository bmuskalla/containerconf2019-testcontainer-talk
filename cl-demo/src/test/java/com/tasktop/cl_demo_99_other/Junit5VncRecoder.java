package com.tasktop.cl_demo_99_other;

import java.util.Optional;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.testcontainers.lifecycle.TestDescription;

import com.tasktop.cl_demo_3_advanced.D5_SeleniumTest;

public class Junit5VncRecoder implements TestWatcher {

	@Override
	public void testSuccessful(ExtensionContext context) {
		D5_SeleniumTest.chrome.afterTest(toTestDescription(context), Optional.empty());
	}

	@Override
	public void testFailed(ExtensionContext context, Throwable cause) {
		D5_SeleniumTest.chrome.afterTest(toTestDescription(context), Optional.of(cause));
	}

	private static TestDescription toTestDescription(ExtensionContext context) {
		return new TestDescription() {
			@Override
			public String getTestId() {
				return context.getDisplayName();
			}

			@Override
			public String getFilesystemFriendlyName() {
				return context.getTestInstance().get().getClass().getSimpleName() + "-"
						+ context.getTestMethod().get().getName();
			}
		};
	}
}