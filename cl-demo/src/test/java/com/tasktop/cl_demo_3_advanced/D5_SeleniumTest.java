package com.tasktop.cl_demo_3_advanced;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.tasktop.cl_demo_99_other.Junit5VncRecoder;

@ExtendWith(Junit5VncRecoder.class) // workaround; not needed in 1.13.0
@Testcontainers
public class D5_SeleniumTest {

	@Container
	public static BrowserWebDriverContainer<?> chrome = new BrowserWebDriverContainer<>()
			.withCapabilities(new ChromeOptions())
	        .withRecordingMode(VncRecordingMode.RECORD_ALL, new File("./selenium/"));

	@Test
	void testName() throws Exception {
		RemoteWebDriver driver = chrome.getWebDriver();
		driver.get("https://www.continuouslifecycle.de/");
		driver.findElementByLinkText("Programm").click();
	}

}
