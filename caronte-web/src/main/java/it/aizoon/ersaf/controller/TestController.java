package it.aizoon.ersaf.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.security.SecurityUtils;

/**
 * @author ff
 */
@RestController
public class TestController extends BaseController {

	@GetMapping(value = "/public/ping")
	public ResponseEntity<String> testPing(Model model) throws BusinessException {
		return ResponseEntity.ok("it works!");
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/me")
	public ResponseEntity<UtenteDTO> testMe(Model model) throws BusinessException {
		return ResponseEntity.ok(SecurityUtils.getUtenteLoggato());
	}

	@PreAuthorize("hasRole('READ')")
	@GetMapping(value = "/test/read")
	public ResponseEntity<String> testAccessRead(Model model) throws BusinessException {
		return ResponseEntity.ok("puoi accedere ai contenuti in lettura");
	}

	@PreAuthorize("hasRole('WRITE')") // sinonimo di hasAuthority('ROLE_WRITE')
	@GetMapping(value = "/test/write")
	public ResponseEntity<String> testAccessWrite(Model model) throws BusinessException {
		return ResponseEntity.ok("puoi accedere ai contenuti in scrittura");
	}

	@PreAuthorize("hasAuthority('GROUP_ADMIN')")
	@GetMapping(value = "/test/admin")
	public ResponseEntity<String> testGroupAdmin(Model model) throws BusinessException {
		return ResponseEntity.ok("appartieni al gruppo ADMIN");
	}

}
