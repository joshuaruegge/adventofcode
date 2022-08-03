package advent.aoc2018;

import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day01 implements IDay {

	String input = "-16\r\n"
			+ "+12\r\n"
			+ "-18\r\n"
			+ "-1\r\n"
			+ "+5\r\n"
			+ "-8\r\n"
			+ "+9\r\n"
			+ "-15\r\n"
			+ "+12\r\n"
			+ "+6\r\n"
			+ "+11\r\n"
			+ "+7\r\n"
			+ "-9\r\n"
			+ "+13\r\n"
			+ "+5\r\n"
			+ "-4\r\n"
			+ "-4\r\n"
			+ "-2\r\n"
			+ "-5\r\n"
			+ "+19\r\n"
			+ "+4\r\n"
			+ "+14\r\n"
			+ "+7\r\n"
			+ "+8\r\n"
			+ "-16\r\n"
			+ "-9\r\n"
			+ "+16\r\n"
			+ "+8\r\n"
			+ "-11\r\n"
			+ "-7\r\n"
			+ "+12\r\n"
			+ "+8\r\n"
			+ "+13\r\n"
			+ "+11\r\n"
			+ "+12\r\n"
			+ "-19\r\n"
			+ "+11\r\n"
			+ "+7\r\n"
			+ "+9\r\n"
			+ "-7\r\n"
			+ "-16\r\n"
			+ "-5\r\n"
			+ "+11\r\n"
			+ "-1\r\n"
			+ "+8\r\n"
			+ "+5\r\n"
			+ "+12\r\n"
			+ "-1\r\n"
			+ "-1\r\n"
			+ "+6\r\n"
			+ "-2\r\n"
			+ "-12\r\n"
			+ "+6\r\n"
			+ "-18\r\n"
			+ "+11\r\n"
			+ "+5\r\n"
			+ "+13\r\n"
			+ "-12\r\n"
			+ "-15\r\n"
			+ "-8\r\n"
			+ "-13\r\n"
			+ "+2\r\n"
			+ "-11\r\n"
			+ "+1\r\n"
			+ "-14\r\n"
			+ "-6\r\n"
			+ "+11\r\n"
			+ "-15\r\n"
			+ "+11\r\n"
			+ "+8\r\n"
			+ "+18\r\n"
			+ "-8\r\n"
			+ "+18\r\n"
			+ "-7\r\n"
			+ "-9\r\n"
			+ "-24\r\n"
			+ "-12\r\n"
			+ "+2\r\n"
			+ "+20\r\n"
			+ "-9\r\n"
			+ "-5\r\n"
			+ "-14\r\n"
			+ "-6\r\n"
			+ "+16\r\n"
			+ "-1\r\n"
			+ "-12\r\n"
			+ "-16\r\n"
			+ "+8\r\n"
			+ "+9\r\n"
			+ "-15\r\n"
			+ "-8\r\n"
			+ "-2\r\n"
			+ "-4\r\n"
			+ "-16\r\n"
			+ "-18\r\n"
			+ "-8\r\n"
			+ "+10\r\n"
			+ "+10\r\n"
			+ "+2\r\n"
			+ "+3\r\n"
			+ "-8\r\n"
			+ "-10\r\n"
			+ "-1\r\n"
			+ "-2\r\n"
			+ "+20\r\n"
			+ "-5\r\n"
			+ "-4\r\n"
			+ "-13\r\n"
			+ "+10\r\n"
			+ "+9\r\n"
			+ "-12\r\n"
			+ "-19\r\n"
			+ "+15\r\n"
			+ "-4\r\n"
			+ "-13\r\n"
			+ "-11\r\n"
			+ "-9\r\n"
			+ "-4\r\n"
			+ "-12\r\n"
			+ "+3\r\n"
			+ "-7\r\n"
			+ "-4\r\n"
			+ "+13\r\n"
			+ "+8\r\n"
			+ "-5\r\n"
			+ "+10\r\n"
			+ "-11\r\n"
			+ "+7\r\n"
			+ "+10\r\n"
			+ "+13\r\n"
			+ "+10\r\n"
			+ "-17\r\n"
			+ "-21\r\n"
			+ "+11\r\n"
			+ "+3\r\n"
			+ "+9\r\n"
			+ "-15\r\n"
			+ "-11\r\n"
			+ "+15\r\n"
			+ "+10\r\n"
			+ "-3\r\n"
			+ "+17\r\n"
			+ "+6\r\n"
			+ "+11\r\n"
			+ "+16\r\n"
			+ "+19\r\n"
			+ "+8\r\n"
			+ "+8\r\n"
			+ "+4\r\n"
			+ "+9\r\n"
			+ "+7\r\n"
			+ "+15\r\n"
			+ "+2\r\n"
			+ "+15\r\n"
			+ "+22\r\n"
			+ "+19\r\n"
			+ "+11\r\n"
			+ "+3\r\n"
			+ "+19\r\n"
			+ "+9\r\n"
			+ "-4\r\n"
			+ "+9\r\n"
			+ "-19\r\n"
			+ "-16\r\n"
			+ "+6\r\n"
			+ "+2\r\n"
			+ "-3\r\n"
			+ "+5\r\n"
			+ "+5\r\n"
			+ "-18\r\n"
			+ "+1\r\n"
			+ "-14\r\n"
			+ "-6\r\n"
			+ "-20\r\n"
			+ "+10\r\n"
			+ "-13\r\n"
			+ "+19\r\n"
			+ "-18\r\n"
			+ "-17\r\n"
			+ "+5\r\n"
			+ "-39\r\n"
			+ "+2\r\n"
			+ "+33\r\n"
			+ "+2\r\n"
			+ "+24\r\n"
			+ "-16\r\n"
			+ "+45\r\n"
			+ "+9\r\n"
			+ "+21\r\n"
			+ "+6\r\n"
			+ "+20\r\n"
			+ "+4\r\n"
			+ "-12\r\n"
			+ "-7\r\n"
			+ "-3\r\n"
			+ "+8\r\n"
			+ "+12\r\n"
			+ "+5\r\n"
			+ "-1\r\n"
			+ "+8\r\n"
			+ "+18\r\n"
			+ "-13\r\n"
			+ "-9\r\n"
			+ "+3\r\n"
			+ "+16\r\n"
			+ "-4\r\n"
			+ "+15\r\n"
			+ "+13\r\n"
			+ "-10\r\n"
			+ "-8\r\n"
			+ "-13\r\n"
			+ "-21\r\n"
			+ "+15\r\n"
			+ "+17\r\n"
			+ "+13\r\n"
			+ "-4\r\n"
			+ "-18\r\n"
			+ "-15\r\n"
			+ "+3\r\n"
			+ "+11\r\n"
			+ "+5\r\n"
			+ "+3\r\n"
			+ "+10\r\n"
			+ "-11\r\n"
			+ "+9\r\n"
			+ "+19\r\n"
			+ "-2\r\n"
			+ "+9\r\n"
			+ "+3\r\n"
			+ "-13\r\n"
			+ "+2\r\n"
			+ "-7\r\n"
			+ "-14\r\n"
			+ "-2\r\n"
			+ "-7\r\n"
			+ "-19\r\n"
			+ "+7\r\n"
			+ "+3\r\n"
			+ "-13\r\n"
			+ "+6\r\n"
			+ "+10\r\n"
			+ "+5\r\n"
			+ "-9\r\n"
			+ "-20\r\n"
			+ "-8\r\n"
			+ "+5\r\n"
			+ "-17\r\n"
			+ "+11\r\n"
			+ "-12\r\n"
			+ "+17\r\n"
			+ "-12\r\n"
			+ "-14\r\n"
			+ "+24\r\n"
			+ "-22\r\n"
			+ "-13\r\n"
			+ "+15\r\n"
			+ "+15\r\n"
			+ "+1\r\n"
			+ "-4\r\n"
			+ "+13\r\n"
			+ "-30\r\n"
			+ "+12\r\n"
			+ "+17\r\n"
			+ "+8\r\n"
			+ "+45\r\n"
			+ "+4\r\n"
			+ "-3\r\n"
			+ "-5\r\n"
			+ "+3\r\n"
			+ "-9\r\n"
			+ "+13\r\n"
			+ "+8\r\n"
			+ "+14\r\n"
			+ "+2\r\n"
			+ "-12\r\n"
			+ "-3\r\n"
			+ "+9\r\n"
			+ "+20\r\n"
			+ "+7\r\n"
			+ "+1\r\n"
			+ "+1\r\n"
			+ "-17\r\n"
			+ "-10\r\n"
			+ "+3\r\n"
			+ "-2\r\n"
			+ "+4\r\n"
			+ "-6\r\n"
			+ "+16\r\n"
			+ "-8\r\n"
			+ "+15\r\n"
			+ "+16\r\n"
			+ "+8\r\n"
			+ "-11\r\n"
			+ "-14\r\n"
			+ "-4\r\n"
			+ "+2\r\n"
			+ "+15\r\n"
			+ "+19\r\n"
			+ "+12\r\n"
			+ "+14\r\n"
			+ "+17\r\n"
			+ "-18\r\n"
			+ "-18\r\n"
			+ "-18\r\n"
			+ "-1\r\n"
			+ "-1\r\n"
			+ "-7\r\n"
			+ "-17\r\n"
			+ "-2\r\n"
			+ "-18\r\n"
			+ "+15\r\n"
			+ "-3\r\n"
			+ "+5\r\n"
			+ "-20\r\n"
			+ "+39\r\n"
			+ "-1\r\n"
			+ "+6\r\n"
			+ "+8\r\n"
			+ "+10\r\n"
			+ "+16\r\n"
			+ "-19\r\n"
			+ "+11\r\n"
			+ "+19\r\n"
			+ "+15\r\n"
			+ "-6\r\n"
			+ "+19\r\n"
			+ "-8\r\n"
			+ "+6\r\n"
			+ "+1\r\n"
			+ "-2\r\n"
			+ "-20\r\n"
			+ "-4\r\n"
			+ "+18\r\n"
			+ "+2\r\n"
			+ "-17\r\n"
			+ "-10\r\n"
			+ "-10\r\n"
			+ "-12\r\n"
			+ "+18\r\n"
			+ "+2\r\n"
			+ "-16\r\n"
			+ "-8\r\n"
			+ "+14\r\n"
			+ "-15\r\n"
			+ "+12\r\n"
			+ "-14\r\n"
			+ "-19\r\n"
			+ "-16\r\n"
			+ "-21\r\n"
			+ "-17\r\n"
			+ "+21\r\n"
			+ "-12\r\n"
			+ "-34\r\n"
			+ "-22\r\n"
			+ "-25\r\n"
			+ "-15\r\n"
			+ "+14\r\n"
			+ "-12\r\n"
			+ "-10\r\n"
			+ "+11\r\n"
			+ "-20\r\n"
			+ "+4\r\n"
			+ "-6\r\n"
			+ "-11\r\n"
			+ "+14\r\n"
			+ "+21\r\n"
			+ "+74\r\n"
			+ "+40\r\n"
			+ "+3\r\n"
			+ "+15\r\n"
			+ "-4\r\n"
			+ "+16\r\n"
			+ "+12\r\n"
			+ "+3\r\n"
			+ "+6\r\n"
			+ "+2\r\n"
			+ "+11\r\n"
			+ "+10\r\n"
			+ "-3\r\n"
			+ "+17\r\n"
			+ "-5\r\n"
			+ "+10\r\n"
			+ "+13\r\n"
			+ "+5\r\n"
			+ "-13\r\n"
			+ "-4\r\n"
			+ "+13\r\n"
			+ "-8\r\n"
			+ "+17\r\n"
			+ "-2\r\n"
			+ "+6\r\n"
			+ "-12\r\n"
			+ "+7\r\n"
			+ "+2\r\n"
			+ "+13\r\n"
			+ "-2\r\n"
			+ "-9\r\n"
			+ "+18\r\n"
			+ "+15\r\n"
			+ "-11\r\n"
			+ "+4\r\n"
			+ "+3\r\n"
			+ "-21\r\n"
			+ "-17\r\n"
			+ "+3\r\n"
			+ "+12\r\n"
			+ "-1\r\n"
			+ "-12\r\n"
			+ "-3\r\n"
			+ "-20\r\n"
			+ "-10\r\n"
			+ "+13\r\n"
			+ "-6\r\n"
			+ "-4\r\n"
			+ "-20\r\n"
			+ "+7\r\n"
			+ "+15\r\n"
			+ "-6\r\n"
			+ "-15\r\n"
			+ "-26\r\n"
			+ "+13\r\n"
			+ "+6\r\n"
			+ "-23\r\n"
			+ "-9\r\n"
			+ "-1\r\n"
			+ "+44\r\n"
			+ "+8\r\n"
			+ "-14\r\n"
			+ "+26\r\n"
			+ "+13\r\n"
			+ "+5\r\n"
			+ "+31\r\n"
			+ "+24\r\n"
			+ "+7\r\n"
			+ "-2\r\n"
			+ "+7\r\n"
			+ "-4\r\n"
			+ "+17\r\n"
			+ "-16\r\n"
			+ "-3\r\n"
			+ "+36\r\n"
			+ "-10\r\n"
			+ "-22\r\n"
			+ "-11\r\n"
			+ "+62\r\n"
			+ "-7\r\n"
			+ "+21\r\n"
			+ "-17\r\n"
			+ "-10\r\n"
			+ "-18\r\n"
			+ "+11\r\n"
			+ "+4\r\n"
			+ "+5\r\n"
			+ "-23\r\n"
			+ "-26\r\n"
			+ "-40\r\n"
			+ "+2\r\n"
			+ "+32\r\n"
			+ "+62\r\n"
			+ "+23\r\n"
			+ "+45\r\n"
			+ "+109\r\n"
			+ "-37\r\n"
			+ "-10\r\n"
			+ "-99\r\n"
			+ "-210\r\n"
			+ "-21\r\n"
			+ "-33\r\n"
			+ "+379\r\n"
			+ "+262\r\n"
			+ "+66\r\n"
			+ "+66407\r\n"
			+ "+13\r\n"
			+ "-8\r\n"
			+ "-14\r\n"
			+ "-16\r\n"
			+ "-6\r\n"
			+ "-19\r\n"
			+ "+12\r\n"
			+ "+15\r\n"
			+ "-10\r\n"
			+ "-19\r\n"
			+ "-8\r\n"
			+ "+12\r\n"
			+ "+13\r\n"
			+ "-11\r\n"
			+ "+18\r\n"
			+ "+7\r\n"
			+ "-11\r\n"
			+ "+14\r\n"
			+ "-12\r\n"
			+ "-11\r\n"
			+ "-2\r\n"
			+ "+4\r\n"
			+ "-19\r\n"
			+ "-9\r\n"
			+ "+3\r\n"
			+ "-5\r\n"
			+ "+7\r\n"
			+ "-4\r\n"
			+ "+9\r\n"
			+ "-19\r\n"
			+ "+20\r\n"
			+ "+7\r\n"
			+ "-14\r\n"
			+ "-2\r\n"
			+ "-7\r\n"
			+ "-8\r\n"
			+ "-2\r\n"
			+ "-16\r\n"
			+ "+8\r\n"
			+ "-10\r\n"
			+ "+1\r\n"
			+ "-4\r\n"
			+ "-16\r\n"
			+ "+2\r\n"
			+ "+1\r\n"
			+ "-13\r\n"
			+ "-2\r\n"
			+ "-3\r\n"
			+ "-16\r\n"
			+ "+5\r\n"
			+ "-6\r\n"
			+ "-17\r\n"
			+ "+3\r\n"
			+ "+9\r\n"
			+ "+14\r\n"
			+ "+1\r\n"
			+ "-11\r\n"
			+ "+6\r\n"
			+ "-2\r\n"
			+ "-12\r\n"
			+ "-10\r\n"
			+ "+11\r\n"
			+ "+19\r\n"
			+ "-9\r\n"
			+ "+5\r\n"
			+ "-11\r\n"
			+ "-8\r\n"
			+ "-17\r\n"
			+ "-19\r\n"
			+ "+8\r\n"
			+ "-3\r\n"
			+ "-17\r\n"
			+ "+13\r\n"
			+ "+14\r\n"
			+ "-16\r\n"
			+ "-8\r\n"
			+ "+19\r\n"
			+ "+17\r\n"
			+ "-16\r\n"
			+ "-16\r\n"
			+ "-13\r\n"
			+ "-11\r\n"
			+ "+9\r\n"
			+ "-19\r\n"
			+ "+6\r\n"
			+ "+12\r\n"
			+ "+5\r\n"
			+ "-12\r\n"
			+ "-12\r\n"
			+ "-16\r\n"
			+ "-14\r\n"
			+ "-6\r\n"
			+ "+7\r\n"
			+ "+8\r\n"
			+ "-12\r\n"
			+ "-18\r\n"
			+ "+12\r\n"
			+ "+13\r\n"
			+ "-17\r\n"
			+ "+3\r\n"
			+ "-5\r\n"
			+ "+1\r\n"
			+ "+6\r\n"
			+ "-4\r\n"
			+ "-18\r\n"
			+ "+1\r\n"
			+ "+11\r\n"
			+ "+2\r\n"
			+ "-8\r\n"
			+ "-15\r\n"
			+ "-17\r\n"
			+ "+16\r\n"
			+ "-10\r\n"
			+ "-12\r\n"
			+ "+16\r\n"
			+ "-14\r\n"
			+ "-1\r\n"
			+ "-11\r\n"
			+ "+5\r\n"
			+ "+14\r\n"
			+ "+20\r\n"
			+ "+16\r\n"
			+ "-18\r\n"
			+ "+16\r\n"
			+ "+17\r\n"
			+ "+13\r\n"
			+ "-9\r\n"
			+ "-6\r\n"
			+ "-16\r\n"
			+ "+19\r\n"
			+ "+13\r\n"
			+ "+8\r\n"
			+ "-13\r\n"
			+ "+1\r\n"
			+ "+19\r\n"
			+ "-12\r\n"
			+ "-20\r\n"
			+ "-20\r\n"
			+ "-11\r\n"
			+ "-12\r\n"
			+ "+4\r\n"
			+ "+12\r\n"
			+ "+6\r\n"
			+ "-17\r\n"
			+ "-9\r\n"
			+ "-3\r\n"
			+ "-18\r\n"
			+ "-15\r\n"
			+ "-2\r\n"
			+ "-5\r\n"
			+ "+17\r\n"
			+ "+12\r\n"
			+ "-8\r\n"
			+ "+4\r\n"
			+ "-12\r\n"
			+ "-7\r\n"
			+ "-18\r\n"
			+ "-4\r\n"
			+ "-1\r\n"
			+ "-19\r\n"
			+ "+1\r\n"
			+ "-7\r\n"
			+ "-8\r\n"
			+ "+5\r\n"
			+ "+1\r\n"
			+ "+19\r\n"
			+ "-7\r\n"
			+ "-19\r\n"
			+ "+18\r\n"
			+ "+12\r\n"
			+ "+6\r\n"
			+ "+19\r\n"
			+ "+8\r\n"
			+ "-1\r\n"
			+ "+20\r\n"
			+ "-17\r\n"
			+ "-16\r\n"
			+ "-5\r\n"
			+ "-1\r\n"
			+ "-3\r\n"
			+ "+6\r\n"
			+ "-13\r\n"
			+ "-2\r\n"
			+ "+6\r\n"
			+ "-8\r\n"
			+ "+12\r\n"
			+ "-13\r\n"
			+ "-3\r\n"
			+ "-13\r\n"
			+ "+8\r\n"
			+ "+4\r\n"
			+ "-16\r\n"
			+ "+19\r\n"
			+ "+18\r\n"
			+ "+16\r\n"
			+ "-13\r\n"
			+ "-1\r\n"
			+ "+3\r\n"
			+ "+9\r\n"
			+ "+18\r\n"
			+ "+1\r\n"
			+ "+3\r\n"
			+ "+14\r\n"
			+ "+10\r\n"
			+ "+3\r\n"
			+ "+1\r\n"
			+ "-8\r\n"
			+ "+18\r\n"
			+ "+4\r\n"
			+ "+26\r\n"
			+ "+22\r\n"
			+ "+13\r\n"
			+ "-12\r\n"
			+ "-7\r\n"
			+ "-3\r\n"
			+ "+6\r\n"
			+ "-17\r\n"
			+ "+5\r\n"
			+ "+14\r\n"
			+ "-7\r\n"
			+ "+12\r\n"
			+ "+10\r\n"
			+ "-7\r\n"
			+ "+8\r\n"
			+ "-7\r\n"
			+ "+18\r\n"
			+ "+17\r\n"
			+ "-6\r\n"
			+ "+12\r\n"
			+ "+17\r\n"
			+ "+2\r\n"
			+ "+3\r\n"
			+ "-9\r\n"
			+ "+18\r\n"
			+ "+10\r\n"
			+ "-17\r\n"
			+ "-10\r\n"
			+ "-2\r\n"
			+ "-13\r\n"
			+ "+6\r\n"
			+ "+4\r\n"
			+ "+17\r\n"
			+ "+12\r\n"
			+ "+14\r\n"
			+ "-15\r\n"
			+ "+5\r\n"
			+ "+17\r\n"
			+ "+4\r\n"
			+ "-1\r\n"
			+ "-14\r\n"
			+ "-19\r\n"
			+ "-1\r\n"
			+ "+5\r\n"
			+ "-15\r\n"
			+ "-16\r\n"
			+ "+1\r\n"
			+ "-11\r\n"
			+ "-13\r\n"
			+ "-5\r\n"
			+ "+15\r\n"
			+ "-4\r\n"
			+ "-15\r\n"
			+ "+1\r\n"
			+ "+22\r\n"
			+ "+17\r\n"
			+ "-20\r\n"
			+ "-21\r\n"
			+ "+13\r\n"
			+ "+1\r\n"
			+ "-2\r\n"
			+ "+17\r\n"
			+ "+23\r\n"
			+ "-35\r\n"
			+ "-14\r\n"
			+ "-18\r\n"
			+ "+6\r\n"
			+ "+1\r\n"
			+ "+1\r\n"
			+ "-13\r\n"
			+ "-10\r\n"
			+ "+34\r\n"
			+ "+35\r\n"
			+ "+8\r\n"
			+ "+4\r\n"
			+ "+14\r\n"
			+ "+24\r\n"
			+ "+16\r\n"
			+ "-5\r\n"
			+ "-2\r\n"
			+ "+10\r\n"
			+ "+16\r\n"
			+ "-10\r\n"
			+ "-11\r\n"
			+ "+1\r\n"
			+ "-2\r\n"
			+ "-3\r\n"
			+ "+7\r\n"
			+ "+5\r\n"
			+ "+20\r\n"
			+ "-14\r\n"
			+ "+2\r\n"
			+ "+2\r\n"
			+ "-5\r\n"
			+ "+14\r\n"
			+ "-18\r\n"
			+ "+23\r\n"
			+ "-3\r\n"
			+ "+2\r\n"
			+ "+4\r\n"
			+ "+2\r\n"
			+ "+7\r\n"
			+ "+8\r\n"
			+ "+3\r\n"
			+ "-16\r\n"
			+ "-5\r\n"
			+ "+4\r\n"
			+ "+12\r\n"
			+ "-20\r\n"
			+ "-15\r\n"
			+ "-22\r\n"
			+ "+5\r\n"
			+ "-10\r\n"
			+ "+31\r\n"
			+ "-2\r\n"
			+ "+9\r\n"
			+ "+10\r\n"
			+ "-14\r\n"
			+ "+18\r\n"
			+ "+6\r\n"
			+ "+3\r\n"
			+ "+19\r\n"
			+ "-15\r\n"
			+ "+9\r\n"
			+ "+7\r\n"
			+ "+18\r\n"
			+ "-1\r\n"
			+ "-8\r\n"
			+ "+19\r\n"
			+ "+12\r\n"
			+ "+1\r\n"
			+ "+16\r\n"
			+ "-5\r\n"
			+ "-4\r\n"
			+ "-9\r\n"
			+ "+16\r\n"
			+ "+13\r\n"
			+ "+2\r\n"
			+ "+7\r\n"
			+ "+5\r\n"
			+ "-3\r\n"
			+ "-19\r\n"
			+ "+12\r\n"
			+ "+9\r\n"
			+ "+2\r\n"
			+ "+9\r\n"
			+ "+16\r\n"
			+ "-17\r\n"
			+ "-13\r\n"
			+ "+12\r\n"
			+ "-5\r\n"
			+ "+11\r\n"
			+ "+4\r\n"
			+ "-1\r\n"
			+ "+2\r\n"
			+ "-6\r\n"
			+ "+18\r\n"
			+ "+19\r\n"
			+ "-10\r\n"
			+ "+3\r\n"
			+ "-19\r\n"
			+ "-19\r\n"
			+ "-13\r\n"
			+ "-16\r\n"
			+ "-2\r\n"
			+ "-13\r\n"
			+ "+8\r\n"
			+ "+18\r\n"
			+ "+6\r\n"
			+ "-11\r\n"
			+ "+2\r\n"
			+ "-16\r\n"
			+ "+11\r\n"
			+ "+12\r\n"
			+ "-20\r\n"
			+ "+18\r\n"
			+ "+17\r\n"
			+ "+14\r\n"
			+ "-13\r\n"
			+ "+26\r\n"
			+ "-1\r\n"
			+ "+20\r\n"
			+ "-1\r\n"
			+ "-17\r\n"
			+ "-11\r\n"
			+ "-8\r\n"
			+ "+13\r\n"
			+ "-7\r\n"
			+ "-5\r\n"
			+ "-5\r\n"
			+ "-7\r\n"
			+ "+18\r\n"
			+ "-20\r\n"
			+ "-4\r\n"
			+ "+29\r\n"
			+ "-2\r\n"
			+ "+21\r\n"
			+ "+21\r\n"
			+ "+16\r\n"
			+ "+15\r\n"
			+ "+14\r\n"
			+ "+21\r\n"
			+ "-15\r\n"
			+ "-14\r\n"
			+ "+17\r\n"
			+ "+5\r\n"
			+ "-10\r\n"
			+ "-27\r\n"
			+ "-18\r\n"
			+ "+10\r\n"
			+ "-20\r\n"
			+ "+34\r\n"
			+ "-13\r\n"
			+ "+18\r\n"
			+ "+17\r\n"
			+ "-21\r\n"
			+ "+32\r\n"
			+ "+13\r\n"
			+ "+6\r\n"
			+ "+6\r\n"
			+ "+28\r\n"
			+ "-37\r\n"
			+ "+20\r\n"
			+ "+29\r\n"
			+ "-25\r\n"
			+ "-69\r\n"
			+ "-57\r\n"
			+ "-3\r\n"
			+ "-59\r\n"
			+ "+3\r\n"
			+ "-21\r\n"
			+ "-9\r\n"
			+ "+14\r\n"
			+ "-19\r\n"
			+ "-18\r\n"
			+ "+20\r\n"
			+ "-5\r\n"
			+ "+15\r\n"
			+ "+5\r\n"
			+ "-9\r\n"
			+ "-24\r\n"
			+ "+20\r\n"
			+ "-14\r\n"
			+ "-10\r\n"
			+ "-15\r\n"
			+ "+30\r\n"
			+ "+21\r\n"
			+ "-9\r\n"
			+ "-34\r\n"
			+ "+4\r\n"
			+ "-31\r\n"
			+ "+25\r\n"
			+ "-85\r\n"
			+ "+25\r\n"
			+ "+38\r\n"
			+ "-36\r\n"
			+ "-28\r\n"
			+ "+58\r\n"
			+ "+121\r\n"
			+ "-31\r\n"
			+ "+13\r\n"
			+ "+202\r\n"
			+ "+66221\r\n"
			+ "-5\r\n"
			+ "-19\r\n"
			+ "-10\r\n"
			+ "-7\r\n"
			+ "-8\r\n"
			+ "-2\r\n"
			+ "+6\r\n"
			+ "-5\r\n"
			+ "-6\r\n"
			+ "+8\r\n"
			+ "-17\r\n"
			+ "+10\r\n"
			+ "+18\r\n"
			+ "+16\r\n"
			+ "+3\r\n"
			+ "-12\r\n"
			+ "-2\r\n"
			+ "-11\r\n"
			+ "+14\r\n"
			+ "+7\r\n"
			+ "+11\r\n"
			+ "-1\r\n"
			+ "-5\r\n"
			+ "+10\r\n"
			+ "+9\r\n"
			+ "-5\r\n"
			+ "-3\r\n"
			+ "-3\r\n"
			+ "-11\r\n"
			+ "-13\r\n"
			+ "+18\r\n"
			+ "-8\r\n"
			+ "-5\r\n"
			+ "+18\r\n"
			+ "+17\r\n"
			+ "-6\r\n"
			+ "+3\r\n"
			+ "+19\r\n"
			+ "-18\r\n"
			+ "+5\r\n"
			+ "+5\r\n"
			+ "-4\r\n"
			+ "-12\r\n"
			+ "+7\r\n"
			+ "+14\r\n"
			+ "+19\r\n"
			+ "-6\r\n"
			+ "+10\r\n"
			+ "-8\r\n"
			+ "-11\r\n"
			+ "+10\r\n"
			+ "-17\r\n"
			+ "+9\r\n"
			+ "+11\r\n"
			+ "+7\r\n"
			+ "-133358";
	
	@Override
	public String part1() {
		int frequency = 0;
		for(String s : input.split("\r\n")) {
			frequency += Integer.parseInt(s);
		}
		return Integer.toString(frequency);
	}

	@Override
	public String part2() {
		int frequency = 0;
		HashSet<Integer> frequencies = new HashSet<Integer>();
		frequencies.add(0);
		//loop over input as necessary till input is found
		while(true) {
			for(String s : input.split("\r\n")) {
				frequency += Integer.parseInt(s);
				//if add fails, frequency is already present in set - break out of loop
				if(!frequencies.add(frequency))
					return Integer.toString(frequency);
				
			}
		}
	}

	public static void main(String[] args) {
		DayRunner.run(new Day01());
	}

}
