/**
 * JEE6 gradle-based Demo App
 *
 * File: DomainValidationTest.java, 25.07.2014, 10:49:55, mreinhardt
 *
 * @project https://github.com/hypery2k/gradleJEE
 *
 * @copyright 2014 Martin Reinhardt contact@martinreinhardt-online.de
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package de.martinreinhardt.jee.domain.validation;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

import de.martinreinhardt.jee.domain.Member;
import de.martinreinhardt.jee.util.ValidationTestBase;

/**
 * Simple validation example unit test
 * 
 * @author mreinhardt
 * 
 */
public class DomainValidationTest extends ValidationTestBase {

	/**
	 * Checks if valid member is showing no errors
	 */
	@Test
	public void testValidMemberr() {
		
		Member validMember = new Member();
		validMember.setEmail("abc@test.de");
		validMember.setName("Max");
		validMember.setPhoneNumber("1234567890");

		// check validation
		Set<ConstraintViolation<Member>> constraintViolations = validator.validate(validMember);
		assertEquals(0, constraintViolations.size());
	}

	/**
	 * Checks if valid member is showing errors
	 */
	@Test
	public void testInValidEmail() {
		Member validMember = new Member();
		validMember.setEmail("abc_test.de");
		validMember.setName("Max");
		validMember.setPhoneNumber("1234567890");

		// check validation
		Set<ConstraintViolation<Member>> constraintViolations = validator.validate(validMember);
		assertEquals(1, constraintViolations.size());
	}

	/**
	 * Checks if valid member is showing errors
	 */
	@Test
	public void testInValidName() {
		Member validMember = new Member();
		validMember.setEmail("abc@test.de");
		validMember.setName("Max11");
		validMember.setPhoneNumber("1234567890");

		// check validation
		Set<ConstraintViolation<Member>> constraintViolations = validator.validate(validMember);
		assertEquals(1, constraintViolations.size());
	}

	/**
	 * Checks if valid member is showing errors
	 */
	@Test
	public void testInValidPhone() {
		Member validMember = new Member();
		validMember.setEmail("abc@test.de");
		validMember.setName("Max");
		validMember.setPhoneNumber("111");

		// check validation
		Set<ConstraintViolation<Member>> constraintViolations = validator.validate(validMember);
		assertEquals(1, constraintViolations.size());
	}

	/**
	 * Checks if valid member is showing errors
	 */
	@Test
	public void testInValidCombined() {
		Member validMember = new Member();
		validMember.setEmail("abc_test.de");
		validMember.setName("Max1");
		validMember.setPhoneNumber("111");

		// check validation
		Set<ConstraintViolation<Member>> constraintViolations = validator.validate(validMember);
		assertEquals(3, constraintViolations.size());
	}

}
