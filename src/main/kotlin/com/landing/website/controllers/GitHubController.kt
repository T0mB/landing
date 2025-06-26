package com.landing.website.controllers

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.client.RestTemplate

@Controller
@RequestMapping("/api/github")
class GitHubController {
    private val GITHUB_API = "https://api.github.com/repos/{owner}/{repo}/contents/{path}"
    private val GITHUB_TOKEN = System.getenv("git_api_key_read")

    @GetMapping("/vleermuis")
    fun getFile(@RequestParam path: String?): ResponseEntity<String> {
        val restTemplate = RestTemplate()

        // Set headers (GitHub API requires User-Agent)
        val headers = HttpHeaders()
        headers["Authorization"] = "token $GITHUB_TOKEN"
        headers["User-Agent"] = "Your-App-Name"

        val entity = HttpEntity<String>(headers)

        // Replace {owner}, {repo}, and {path} with your values
        val url = GITHUB_API
            .replace("{owner}", "T0mB")
            .replace("{repo}", "vleermuis")
            .replace("{path}", "README.md")

        try {
            // Fetch file from GitHub
            val response = restTemplate.exchange(
                url, HttpMethod.GET, entity, String::class.java
            )

            // Return raw JSON (HTMX will handle rendering)
            return ResponseEntity.ok(response.body)
        } catch (e: Exception) {
            return ResponseEntity.status(500).body("Error fetching file.")
        }
    }
}