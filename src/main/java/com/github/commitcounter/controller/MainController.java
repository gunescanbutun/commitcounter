package com.github.commitcounter.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.commitcounter.model.CommitServiceModel;
import com.github.commitcounter.model.CommitViewModel;
import com.github.commitcounter.model.ErrorViewModel;

@SuppressWarnings("deprecation")
@Controller
public class MainController {

	private HttpClient client;
	private String uri;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView modelAndView) throws ClientProtocolException, IOException {

		int maxCommitCount = 0;
		int totalCommitCount = 0;
		String loginName = "";
		uri = "https://api.github.com/repos/facebook/react/contributors";
		client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse response = client.execute(httpGet);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpServletResponse.SC_OK) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String jsonStr = rd.readLine();

			ObjectMapper mapper = new ObjectMapper();
			List<CommitServiceModel> commitList = mapper.readValue(jsonStr, new TypeReference<List<CommitServiceModel>>() {
			});
			
			for(int i=0;i<commitList.size();i++) {
				if(maxCommitCount < commitList.get(i).getContributions()) {
					maxCommitCount = commitList.get(i).getContributions();
					loginName = commitList.get(i).getLogin();
				}
				totalCommitCount += commitList.get(i).getContributions();
			}
			
			CommitViewModel viewModel = new CommitViewModel();
			viewModel.setTotalCommitCount(totalCommitCount);
			viewModel.setLoginName(loginName);
			viewModel.setCommitCount(maxCommitCount);
			modelAndView.addObject("model", viewModel);
			modelAndView.setViewName("index");
		} else {
			ErrorViewModel viewModel = new ErrorViewModel();
			String errorMsg = "ERROR " + statusCode;
			viewModel.setMessage(errorMsg);
			modelAndView.addObject("model", viewModel);
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

}
