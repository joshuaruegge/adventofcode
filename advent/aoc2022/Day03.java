package advent.aoc2022;

import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day03 implements IDay {

	String input = "DMwrszrfMzSSCpLpfCCn\r\n"
			+ "RMvhZhQqlvhMvRtbvbcPclPlncddppLTdppd\r\n"
			+ "tVMQhFtjjWmsFJsmsW\r\n"
			+ "trRtvNhfJhSzzSTFVhQQZQhHGphP\r\n"
			+ "CnLMBWLwDMgMcwwdngdHGPVTQGpTHZdGPGpd\r\n"
			+ "LLDqcDgwqCMnLWqtvzrzbbtJqPjJ\r\n"
			+ "wQQwHNQLmbWQbQRHwHNFBbwqPfjqlzRMGRqzpSfvPlzplM\r\n"
			+ "nCtGCZZtsGsrtDMZpfMpSlMlvlZq\r\n"
			+ "cJctJCgVJsCJnDTsCthGhGLwBWBbbQmLbgQLQQdWbbbQ\r\n"
			+ "ZWnNlTNTnhhQQlDNdmmpwrrrqBwjwjZd\r\n"
			+ "GzvlVRSfvMVMvGlSpdCCdjmfpmBCdsqB\r\n"
			+ "bzlFlLzJWLHHttLL\r\n"
			+ "SmzFhVDzrmSrszVDVhSVbhZcCZdfZNcnMfMbZnNN\r\n"
			+ "PTTRGqwqTqWRwLgTLTZGnCbZbNddZCCtMcNs\r\n"
			+ "sgPqPqgJgWWqjjwgwLLVFBFSVmvmBBrmJJDSvp\r\n"
			+ "CBccfSBhcSBddfgtlJmmmwmPRwmh\r\n"
			+ "FpTzzGWHWprgDtJlDZDPFR\r\n"
			+ "HbbTzWnTrnWtCbQBbQqQbSjf\r\n"
			+ "fPHspCjgwZggSvZQ\r\n"
			+ "RrNhzFZFcNzFLNLNwQlSlLnv\r\n"
			+ "TRFrcDVrrRmrhFRZzVrczqhRpjqjsssCpfHjsCdpsPfpjCMC\r\n"
			+ "ZBnBTMVTSbGbTVTGbCPgqsgPtHtgCcPtBB\r\n"
			+ "ldDrmnnNrzhdhfgcsHqcsfCcsCHg\r\n"
			+ "zFdrzNdzQNDDhFdWldDrJTbZTbLZJVVVpMVWVnLS\r\n"
			+ "pLnpQNhBbnWvbsWm\r\n"
			+ "FrFwjlTPTdTqqrDZWbvmZbpSgmJWvbgS\r\n"
			+ "RqDqRrdGFpGRFrFFdTNzCcHcHLHBzQCcNNGN\r\n"
			+ "bvRCtbtCPfSGtCcvCbPNlglqgqlGZMzTgnlZnq\r\n"
			+ "hrmWWFspsHWrzNwTnFlTMTwFFn\r\n"
			+ "HpjJDWBQmmQdRffbPtSzdJ\r\n"
			+ "GpHjFsjMFpCpMWjMGCqWmmqrWQtmthdbDbbD\r\n"
			+ "fzgLTJwJPSgJgzSzPfhmqmQhQHzQtbQDrrmq\r\n"
			+ "RNlBRwHfRJHLLfHTwLSBppNGvjNMFFCVpVFcvcFC\r\n"
			+ "SfQnfSFHfnvMtQQSnHJtMffsdTlZtdZmtllmTlmlRRbBRLDb\r\n"
			+ "hrwhWWwqzPrcCzwwzmPlRbdmlQDTPPBLDl\r\n"
			+ "CpwCzrwGzNCWrJnMvpMvfJVFvQ\r\n"
			+ "rCRPpgSggcpqrhPrCDDTLsMLDSDGLTMGVs\r\n"
			+ "HdvzmRWmlHzwvWHvRHRvHJbDFsdMssQQVGMDTMDLVLLFLT\r\n"
			+ "JBlBnnWBJlCqZRRqRBpr\r\n"
			+ "GtZllZDlfDpGHZtZBGBZpDmzQzzCSVVFHmmmsPCQWWSS\r\n"
			+ "JvFJJrwvNNcJTnbrTRNRSCzqwSsVPCPQqmCQszVm\r\n"
			+ "JLMnTbLnMgBhDFDf\r\n"
			+ "lffDhtgDJzCJNGGTzWTRRnRvBv\r\n"
			+ "qpbpdwqZwqZSwMPSqdQcQmTRnWvnBnRBQBtVnTvWmB\r\n"
			+ "SccbbwSbZbFPswpSZtgFlClLhgChhhNfJlFj\r\n"
			+ "ClmCjCJBjwBVwJGjlGNFJlVMHSrfpDpTfrHMcHTppQVrHp\r\n"
			+ "dRLZWLvWSHmTccWW\r\n"
			+ "ggtqzmRZnmhghZhZghntdqsvBbjlbNFFBPwNNJNNCBlwPGBz\r\n"
			+ "HZmsFZQpvZsWCZQvWrghGrhtgNzdHddHGh\r\n"
			+ "fWSbqWDJVwcSccNzrNhcBtGcgG\r\n"
			+ "VqVfTJnbWjqTSqbwDRfRvQvFpFLRpZsssQsCQZ\r\n"
			+ "FpFZNfplSTJmbZzddGzhDrWh\r\n"
			+ "LqLPPQjLLRMPqvjLLHQrLqrRWdzHnGhdthttGGbbDWhDDdWz\r\n"
			+ "sMLMgvRLgscrLrRQvwmTNNfpNplglplfmp\r\n"
			+ "MPVBmCmWGWRPPqRhLcnjcvjjcpjMvp\r\n"
			+ "tzwrwsJlrldJsrsrTtrzrTtSNnLJSShnjcncvnvqnSFnqN\r\n"
			+ "rswrzsbdwDHHbWZqVfWV\r\n"
			+ "dVmmMTmBPTrCBRMCqFHSWFFHWzCvCz\r\n"
			+ "jNqfGsDqtsjGjQjDlcJFFFznFtzznvtFpFFp\r\n"
			+ "fNNhgsDcfNflqchVRdgVrRPRdVTRRb\r\n"
			+ "HJPLwgLvjttmgHJFjwHgtlsBbNbbNsPpblspTllThT\r\n"
			+ "MzmcRRrdDMVTzbhNNSszhl\r\n"
			+ "mCDDVqdVcdDrqfcCnrFwtGwvngwvtWJtFjWW\r\n"
			+ "dFDpmttBlvNNgWlglNDBFttmTGHTcSSJJHHnMsJsGGSdqcJj\r\n"
			+ "zLbwMLVQbQRwVsJsSHSsHcJqbj\r\n"
			+ "wfVZLPzfZZmpMZZMBl\r\n"
			+ "PZHZMJSTBWHNWSHzVnhhfnhThhpnpC\r\n"
			+ "jFdBBtrFjpfnjfnf\r\n"
			+ "ccGrbblbGRDQMlvmQBvmBl\r\n"
			+ "PCCTsnbPbHDnlDfDNB\r\n"
			+ "rMjQltgSqtvMjScQggjfVVzBzFHzGfVGDLGBqB\r\n"
			+ "vdtrMSttcdwcpSQSdglMrtWRRPJZCpsRZJmWRRWChWPh\r\n"
			+ "pWzbsPCCPPpbptSMCJJwBQQGQt\r\n"
			+ "cDDmcTTRRqzFRddVTSJwMShMtBwhwQMDwv\r\n"
			+ "HldqVmVlZdLTcmRFdrngNNzrffjWpPLggP\r\n"
			+ "JPqvjJmmqvSLmPtpZdcftdmtfdCC\r\n"
			+ "swwhDRwBBHjFFBtBfZ\r\n"
			+ "RRzNQDwznDsDwWJjLNlrSPLSTr\r\n"
			+ "VQmdRLvDlmqZdFrBBJdW\r\n"
			+ "CMstGsnHnHGGMrMZwMpwBSbW\r\n"
			+ "GnsshssNfjtsnggnHCGhjtmfLQQczllvDRVTTQllQWlQ\r\n"
			+ "dhbNbswbwVdNtVdstBtgbNQTBCCSFTmfmMFmfRqQmmQM\r\n"
			+ "HFljLrvZfMQQQPvm\r\n"
			+ "WrpznLZZrnplpWbgdFcFsNzszgst\r\n"
			+ "LjddfTlMccnBfDQBtBQb\r\n"
			+ "ZRSNchHwhNNCHNSWPQFFFHDBBtnQDH\r\n"
			+ "CNpZshSZgpwJmpdLMlplMc\r\n"
			+ "bTmTFmqzgbBntRVsFvVwcv\r\n"
			+ "CZfMrlZLLLMlfPZRLRHGstnjGwtvGcsSVwtcSGvn\r\n"
			+ "ClZpMLCRMZMrHMLmWpqQBpzpgQzmbg\r\n"
			+ "jDmSSGWDDdWdSqqDDqCqpJzqRRqpJnRsMRcMzM\r\n"
			+ "lPgNPvPrrgNrPhNszFggnRzccbMJgz\r\n"
			+ "ZQTHQvQTZPrrQlBBrNvQZZGtTtGdsVCGsCTLLGDmLsjt\r\n"
			+ "rbCfBrbsvQqRFZRNZC\r\n"
			+ "HLSTcwqwZSQFFgRZ\r\n"
			+ "wdDwjpMHqJDTMTdqjlfBvGBhsbfhbsnb\r\n"
			+ "ZhZcHHHlhgchHhlCZZhLCCbGdrsBBGPNBjGbsjNNjnJnPn\r\n"
			+ "wtJqqwDqQQMQFqSqTzwzVTBnGdsjBdnMdPGBBsBdnrjr\r\n"
			+ "RVzJzmSVZmLLWpCc\r\n"
			+ "gdqjQQrlhhQlQrhsnjjhLgmmvmHBBmTmZRsHJzTBHRJv\r\n"
			+ "NwNnGNbGPbmTGpJzppBG\r\n"
			+ "nDnVDfMDrQqQStgM\r\n"
			+ "MLbgbppMMgLmHgQttGQJgJrBShwNShWBBSNNrwNqNN\r\n"
			+ "GnTFlzCVVwPRrVWhSw\r\n"
			+ "GnDDdvdnZDTdnGMsHbbttZgttLbc\r\n"
			+ "mdmPmjClrTzqttfm\r\n"
			+ "cpFnSbcQQsqNNtqWJzHS\r\n"
			+ "QFpcMMBcZtLpQBjVjZhlPjjVlwvw\r\n"
			+ "spVsPjTZZMpZMVLDjmdSQJCLJSmLzdJQdl\r\n"
			+ "HhRnNrqwMhNhnqnHwGNRFBNBrzSCSdQmQCdddbrQSSclSSbQ\r\n"
			+ "nFNqGRvqBfjMvTssfZ\r\n"
			+ "FjjzjnpFqqzFFqgFSZjBhHfHhnHRDDwfdTdLfD\r\n"
			+ "MmCMGCsMWbtJrtCWCbmsmWWhdLGGwRBwdfdLhdTLhHHTBd\r\n"
			+ "bJmtrRvRjgzFFvVq\r\n"
			+ "RWwWmVQGMFGmMGVCVWRRZSBgDdSdJGlSJpcBGGSlpJ\r\n"
			+ "HHhQThnjBDHfSBlS\r\n"
			+ "bPhNjbsssQzFNQqWmz\r\n"
			+ "FTDtrjqwwqGtDbGnfBlcnLcWBZwlWn\r\n"
			+ "mMhRMvJsJvJnMHCvHmhLZLQlhWQBBfPfLPBZ\r\n"
			+ "HRCCsdNdvNmCvggFStbzjbGSSjjn\r\n"
			+ "sLGddsvvcZmLvrLMGcMsVnTTlqlHCsTHHVVgVt\r\n"
			+ "wRbfJPbpNRffRJMBhpDntTCHFNVgqllFlqggHC\r\n"
			+ "DpfbPhRDJPMJppJwzfpbbDGSjrGZvdccQdjGvQZdvrLz\r\n"
			+ "wTwLNLVTqnLMsBwfMsJmCj\r\n"
			+ "JhlGvcdJhSFvFvvvMfgBpCzjzdCfsMMs\r\n"
			+ "DSlPPJSGWrDcFPhtFhWJZHQZLTQVnRWRbHbZHQQT\r\n"
			+ "TmTgTrPDNLNVlDrmlbgNmrSSGbzjZGMvjpZjvvphWMzW\r\n"
			+ "QtBfdfQcdfHtZcnZnGZzchnp\r\n"
			+ "HQHwRBGfBCGBtsrCNPDTmTlNLr\r\n"
			+ "bfNhjhNJDWhlWhlRRR\r\n"
			+ "SsscnHgnSnZnltqqfWRWrzZv\r\n"
			+ "cnfTMfmMnTnmFGsnTVLLLpQJbpbbjpdTdN\r\n"
			+ "BqwZzqRQQRRPSlFRQDDwdfWwhJphnfgfnpMdJfdM\r\n"
			+ "rcTLrcrvDDChWJhfpTgTJh\r\n"
			+ "DHGbGNVCZStGqSqS\r\n"
			+ "dlfdRNNfVdLwrRnwdwRmhLFsbsJJgLqbgCBWBCsW\r\n"
			+ "PHDppMPMHHDPzmBBCmWJqCmbJD\r\n"
			+ "HzzZHmZzQcNdRRdZwddr\r\n"
			+ "wrlshslPsSRfvrQvwbrslCDghtDgCVhDhBVJCFHddt\r\n"
			+ "mZnGpWpWzGTMqnFqDqJNDNFJVJqH\r\n"
			+ "LjpzGcjMGcTzcmmznWSRsfRPfcrbFQcfrwcv\r\n"
			+ "rWBmmmtNmmtBbtlwnhJJVZbw\r\n"
			+ "FsRcjGdLdvFslZbQJZwQps\r\n"
			+ "GHFGvMccFPjgDNbmWWBTTHNz\r\n"
			+ "GhHzmhmwlpbltGBmBmsZsBZsfCWC\r\n"
			+ "rgrcCCPdsWBgNVBD\r\n"
			+ "RnRMdQPMCqndSdQdcQhblpLLwhJbbpzGzwpS\r\n"
			+ "NNQtStFPpJwhRbRzRbqpZZ\r\n"
			+ "jLnmdJrrdDTdbgWbTbRW\r\n"
			+ "JHvnMCmDnMnMljLCDmMLjHNFGGNBPVtQQFtSNFQtPQBv\r\n"
			+ "BFbBRllFZJnPVJbV\r\n"
			+ "GpGHwgzcLhDcwttwthzzhHcPTjZjMgMVZjgZTMmTnMZWJVJm\r\n"
			+ "GccwhqcDtlrPqQrRNQ\r\n"
			+ "gWHWLgHBHQdFhjGGThTQhR\r\n"
			+ "pZsSMpZMJJSzMszzzqclpfjvrvvcRGGTcTVhbVvRbTGTRG\r\n"
			+ "lnMwsqZqsslpjlSMSsffZqqJBgHNNPNDWdLLgdDgdLHPWwCw\r\n"
			+ "qfNvBCBfBqfNMBqCZZfcnmnvtwScpwFSpSsSwt\r\n"
			+ "HzdVzLWPPGGDdnsswnztsRsnmn\r\n"
			+ "QddWVQgJPPHJTJbjBtNTTq\r\n"
			+ "DdRDDPRGGPGccfcbJwsbJWzsnznlLLWzWTLWhVVVVS\r\n"
			+ "CvCrNCqgFqvmqNZFZqqZvpWlVrlVhlhnTLShlDWnzVBD\r\n"
			+ "jvqpvpvpQNCQQCZZmmNgZfdGddRjJDPRMHcHJDHPJf\r\n"
			+ "ttdtBtPPMqWMdgPPBbVGWfTGTTzSVLfVrzCS\r\n"
			+ "ZpDpvRpZDDcmmjmZfLSrwzRnSVSnwTTR\r\n"
			+ "ZvQmjFVHJFDcQjDlZcDVHdqMNtqNBPtPJtbhhbdbts\r\n"
			+ "dGdwwLLpgwgssJpgssNhpJlnbfjnzFfcbfttGjzjlntf\r\n"
			+ "VQvDvHVVQHrQHDCZVBChrHFtzffnfltFFtncnvFtllMl\r\n"
			+ "VBShSqDVRVSTmppPwwsP\r\n"
			+ "fTFDTLNNzlcNrmDcrMDTFPwCSsbCbPPsnCPwLSPvbs\r\n"
			+ "ttQqhJtBRRGnvgHGnlSnbl\r\n"
			+ "hZBJlQBRjVRBRjhtRRMNFVmFmfDNrfWcFVmD\r\n"
			+ "mcTZFBFmqBjmBgPtCtPprmssStCP\r\n"
			+ "LWDQNqDJfQNJddnWfzhfsPRVppVVsSptftpVMS\r\n"
			+ "NDGnJDDDbzddWdNbGNQQLQbqqFBBFcjlZBlHjlZHGBTvZB\r\n"
			+ "PwDzvphPwVwWBqLLwnJWTq\r\n"
			+ "jdCGCgjmllCrmmlmjrbgmRdgJSSJJFLSSqJfLnqLLLbWffLB\r\n"
			+ "mRdjcMHgDpZhDqMZ\r\n"
			+ "cqLjhhrwZwJbBqZhMwbZZdGWdGSllWFvLFGQdnGFQG\r\n"
			+ "gHHVzzppRVggcgpcGWRQRSvdSvvGWvll\r\n"
			+ "HmNNHtVggHsHPtrhJsbjbwCrCqJc\r\n"
			+ "zqPvzLVvzFFQZzWpRLlmHRDHmRCHDH\r\n"
			+ "dNjnJGGrGdqqMprRlpqB\r\n"
			+ "GsgtjhSsSvvSFqvP\r\n"
			+ "pVrfzzjrjWVWTWjrNZvnJSJZqnnqnpSZZS\r\n"
			+ "bdQVQPRPDdcbRGPFddRVMVlZlMlBqSBBZSvSZwnwvJBS\r\n"
			+ "bFbcFbCPPCbbVHCCdVgWfrzjmWfrWrNWgHfT\r\n"
			+ "JgJqLjjjVGgdqGDZGzlGRStStT\r\n"
			+ "PHrHccmrMrTSMVStRtRR\r\n"
			+ "HWPWffNsrppfPWNsVFsmPNCJwwjdJdvdvnJwghBLJLpdLJ\r\n"
			+ "HtHvcnDSDgDcDHtpLrvwjwjfZMjffw\r\n"
			+ "CPWzdJdqVdWZpnLdwnrfdn\r\n"
			+ "GNCNmTQnPVRRglSlHsSG\r\n"
			+ "FJdhjTPbdPJjTPjTjPtSLsSBWWRcCvCvsBWztc\r\n"
			+ "MfGgrHMDDpMnZGDLCRLScCsBlgWvzB\r\n"
			+ "HnmpmNNHGZZpZZrnMPFFbNCNbFdTPVFFFN\r\n"
			+ "TJrrrJQTqJqmTltfRrgfgtgFFg\r\n"
			+ "jLRzBvBjjcnFBNwWlgBZFt\r\n"
			+ "RMjMCGpGzGznzhRmmPPDPsmMmPQmJs\r\n"
			+ "BZqwQCQZGZcVBczqBHtfbbbWfTqNWfMfPNqW\r\n"
			+ "LLpmFjpvpHrvRFSRDRMWbdbtfPWPbjtMgMtW\r\n"
			+ "SDnrpDprDFnQhZCVnhcH\r\n"
			+ "WTsBBQTfQQTTbJBbZbnfTsRFwFrjwjFlrRqvrrlqvWRV\r\n"
			+ "pGcShcGSLNJNHCLttlpllRFgpRFlRpgRrg\r\n"
			+ "GzcMLScSGJGtCbsbQfbZbMBnBn\r\n"
			+ "NGCLGjVjZjQwTGJRQdWM\r\n"
			+ "cFTcvSrFmnnpSmndMswsRMJWRwMHps\r\n"
			+ "rrrhhcTznqvzmcccvvmhgzqDgbgttlDtjjjlfVCfZCjZZV\r\n"
			+ "ccDMHddWNDnnNWMMzdHJJmSQhfQZfvQZflrZQfdVfLLZ\r\n"
			+ "bgBFRTwFtgqCgpRGFpvpVllZlhjrrlVlvj\r\n"
			+ "wtbBGPTPtRTgbCTBqFgGRwFnsWJnmDMsWMJJMzHPhDmJzP\r\n"
			+ "zsbsMtMMdnffBbzNsBtCCWLpLrCrcNLVDWVVcD\r\n"
			+ "TmPhJRvwmjmhFJwjjRPFPTvJGVCcCGBrDpccpDrCrWCVDVFZ\r\n"
			+ "QvSTvBhqwjPmwddHgtqMnllzMl\r\n"
			+ "gftDtqnpqzGZsFcthbtZ\r\n"
			+ "VlNPrBrRNrLBmdRVFCcGCZTFCsTCsbLL\r\n"
			+ "VdldlljlSNHBsSlqfgqMDDvzpHJHWg\r\n"
			+ "tQDLvFLcDrWrcnsHffCGgGHG\r\n"
			+ "ZRPTPJqhMZJZVllRZJPVZPRHnhCnfdssnCznzGhdgfwCHn\r\n"
			+ "qPqlPVlTlSqbZZVJplqlPDmrjWFtmLtFWgQvtmtFvp\r\n"
			+ "zlZzdNRPgGGzsLGCDBBtCDCtSncScP\r\n"
			+ "vWvHWbqjrFMbvrTWcVnQBBBSjLDcQJcL\r\n"
			+ "wfLHwfFqLFbhHvWhMWqwbwwRspssmzgpzGgmmNfmzmRGRz\r\n"
			+ "rPvLrQBvBLsLLdtrgssgZjwFwlnCFMtMFnlllnnb\r\n"
			+ "mNmmzpWHlzjlJMJb\r\n"
			+ "TVSVTWpqRWpSTqNbTVRBPDfLLPrSLrsfQrrvsf\r\n"
			+ "nRjpQWnQnRQzMjRdrtvvPCfmvGtPfMcCtG\r\n"
			+ "TDbrbhNZVbbbbwhDZDhbTTGfcftqcGVvmmcqcJCcCPmJ\r\n"
			+ "NLhrSwgwgnsLsQWljW\r\n"
			+ "JWqVSpGNPdNNzdZJJpMzHzwLgsMwzwQwMBgL\r\n"
			+ "clrlcvrRfccCtFbHrBWLgwLHmMHsHg\r\n"
			+ "DbfDFjcvRcvchWZVWdNpGZNqdh\r\n"
			+ "sdfvFLfmtszQwLfddRpmtDDBjVNWGMNQVQNMJGWJMj\r\n"
			+ "lccrncTZhqqcqhWggvrjMNMGrJMG\r\n"
			+ "SblShnZCqSbPhhbcbTTSZFdFsFpmdRwPwzvmswLtmm\r\n"
			+ "PGwwHpfnFSvVpWqWCQNNjCbbnW\r\n"
			+ "lmddlhcDRBlLRchdmzbNjqqWTcbNPNWTzz\r\n"
			+ "RBMrRdRhlDtPrJtfwFHpsvrHpFSrFw\r\n"
			+ "hhwlglFFSQndLRFbmCbTTz\r\n"
			+ "NczHMMqzpzPcpfBffcmTrdfGbbRbGrdGrLCL\r\n"
			+ "qNzNPqMjcqNBWWccBHsZPDhJnllwnwvJvQnJhQsgvD\r\n"
			+ "mbmvmvbbprZmlFmZbFgLffgQtFNHNhfqQtNQ\r\n"
			+ "SJcdzjSJBzdBdJDzQhhLQfqzNQQHggRL\r\n"
			+ "jwDwcTTDThvTZPPW\r\n"
			+ "FSVBBBvHvCpVVDDGcGwNNhhctwMvMc\r\n"
			+ "fLLZsZVQmjfTfqQRmQhhtgbbJbGJRghtcGct\r\n"
			+ "qTsTQdqjVfqdVdZZqVLpCpzSpdppBlSpCFdHSC\r\n"
			+ "sQQhWsMmQshlhmMQZFDHDJFjgjzHZgcHdH\r\n"
			+ "LnwnpNRrnrbCqqLpwnqfnLcvFHJFzNcHzJcgJJHgdDgN\r\n"
			+ "wCbnpCfPCVqwwnrrbbPRGMMlSllmlTTmsThVMlsd\r\n"
			+ "pzrprfwgbwtwqzrCWbqCwqSMvddHdDSvtHRlDnRRDddD\r\n"
			+ "zQLzQQjPBPFcLcQFTFsmNQzcMNdDdvnldHHvdvnDnRnlvRnJ\r\n"
			+ "cTZGzzscLcPrqrfrZqqbVV\r\n"
			+ "DcSdcTwDLmcwDwvWssGfJfcJQZPGnfcs\r\n"
			+ "FlHFMgtgNggpsztMHMqpjgBBnCfPflfQnZCQBBCnRPZC\r\n"
			+ "gpVjqNVrHFtjqqzSLDTSmTDwwrmhbs\r\n"
			+ "MLMzJTsZzZMgMLgHMmVmdCVhCBlQwDwwhChD\r\n"
			+ "vtPRQpbqCldwdtBC\r\n"
			+ "bQqFbnQbcFfjPRFPQnTrMMgcJgJrssrzgrgS\r\n"
			+ "mtdGJmQRFmdtQvdvtRtdHzHzqZqpHFzZnCzhZjjH\r\n"
			+ "fPwVlllswMVNPfBDDlNVsMsfcBjchHncqzjZbpzjcqCnpHHn\r\n"
			+ "rlsNPWNlhWTPMMNPfwNWTLQRvQLLmgvSJvRJgTRG\r\n"
			+ "TwnqhqqgvQnGBGmBDp\r\n"
			+ "SMjclJSjjVJgCzCzNgpmdBpmBGspRBmpDDVB\r\n"
			+ "JjMCgMMHMMZNStllZSNHhPqFhFWfqPPqTqhLFqtL\r\n"
			+ "lRQPtjPRlDdStDSlPmvllvLsCphFfCHLHggspgFmsFLH\r\n"
			+ "qwpTNprcbNWVHLrfFssBgFCM\r\n"
			+ "NTWTnzTTWGZZZVRSRRQGpdDtSQRp\r\n"
			+ "gpwTPNPBPTdLLLLVGl\r\n"
			+ "jSHdjzZHMcDVtDvFjtCF\r\n"
			+ "HqfZMHzbcqRRRWgdqPmBBBNmwW\r\n"
			+ "PvSBtdFgvSmBPngFBTBjbSjwwpGjsppMjNpMjj\r\n"
			+ "VZLfVQLzQQQhllpcNcwbssvwwwZj\r\n"
			+ "vHWLVVqWTmTgttgq\r\n"
			+ "CNRmNRFNRCWbWNCrlmfGlWqFLsDZQZBZzgwQZsBsDZZCzczB\r\n"
			+ "MSjdVHvHnDDhHvdwBwssZVzwcgLcQg\r\n"
			+ "HnMMTdttHSHSpHvDddpSHTjWlNWFlmRtRmRbqGfqGGNNfR\r\n"
			+ "fBLTDppznrfTndfnfTzTLPvZvvHVbRbggjvzVbzvbV\r\n"
			+ "mwmDGGlqDhMqthGqhJMWmlNVRZPHjgwjjRZbbHRgRHvv\r\n"
			+ "DmhsJsshWGhSGlmlmrdcLLsTBBfcfnBppc\r\n"
			+ "mbCGFFmGmcdTrCTQdh\r\n"
			+ "MJHfJNLllJffPLRTdBqTRQNcqQGB\r\n"
			+ "fPJHfSSSWfSLDMLWGHDMLDFmznmsjmvZwzvjZjbvbZ\r\n"
			+ "pPvpJSfZTTvCzNZczzQZchcj\r\n"
			+ "svbHWsqsvbsMFtVHgVtcRQcDlQRRRQLjlqjczj\r\n"
			+ "tBsgvHVMFggbgFrgWnwSndfBmmBJfPSfpn\r\n"
			+ "jwbwfjSbwjVSjvZPzWSvhvhQlCsBFgLRLLgBLRClLLFQQw\r\n"
			+ "GdNJHpmHTDnTNJqnFCgBLFLFzFtsQRCd\r\n"
			+ "NpMJHpnMrDpJGTHqTTmJHTPjfcvbWfrffVzvZfVWSbjz\r\n"
			+ "wFwpqWwwpqwtqqrbCFtptDmCcfNhNRzRBZRRJRChVNBZBJ\r\n"
			+ "svlvjHsQlvdlvMLdlvPSSLtzzczcNhJthfNtRcNMJNMc\r\n"
			+ "HvvPLSHjgltjsvqwbbnmWmDpgwTT\r\n"
			+ "zhCmPVwwChdCBtsWnNWswBWr\r\n"
			+ "GJJSfSgFpjJjGgpfpgrcNNstvnBHNnHLtFHr\r\n"
			+ "jgDTfjpMgZMGMGJTMMJRhzZPCzbhVlPqdNCbhd\r\n"
			+ "bDbQQmVDRpDNbRQlfQQZnfwTlllfsT\r\n"
			+ "FChzzBWhVzrgMwffJwlnngnTlJ\r\n"
			+ "MCvqvhFzcHCChjtpNNVLppGmbq\r\n"
			+ "bZZzJnccqdzcLhrcQDLrDs\r\n"
			+ "FfCfWVfjWTFClClfwjWCfGGwhZSDhSLsSSRpZprLph\r\n"
			+ "mFmTMmFjMMWFfZtttflWjmWTngNHJHggJJHtzgnJvBtBgHdv";
	
	String input2 = "vJrwpWtwJgWrhcsFMMfFFhFp\r\n"
			+ "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\r\n"
			+ "PmmdzqPrVvPwwTWBwg\r\n"
			+ "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\r\n"
			+ "ttgJtRGJQctTZtZT\r\n"
			+ "CrZsJsPPZsGzwwsLwLmpwMDw";
	
	@Override
	public String part1() {
		int total = 0;
		for(String s : input.split("\r\n")) {
			HashSet<Character> firstHash = stringToHashSet(s.substring(0,s.length() / 2));
			firstHash.retainAll(stringToHashSet(s.substring(s.length()/2)));
			
			char c = firstHash.stream().findFirst().get();
			
			if(Character.isUpperCase(c)) {
				total += c - 38;
			} else {
				total += c - 96;
			}
		}
		return Integer.toString(total);
	}

	@Override
	public String part2() {
		int total = 0;
		String[] split = input.split("\r\n");
		for(int i = 0; i < split.length; i+=3) {
			HashSet<Character> common = stringToHashSet(split[i]);
			common.retainAll(stringToHashSet(split[i+1]));
			common.retainAll(stringToHashSet(split[i+2]));
			char c = common.stream().findFirst().get();
			
			if(Character.isUpperCase(c)) {
				total += c - 38;
			} else {
				total += c - 96;
			}
		}
		return Integer.toString(total);
	}
	
	public HashSet<Character> stringToHashSet(String s) {
		HashSet<Character> a = new HashSet<Character>();
		for(char c : s.toCharArray())
			a.add(c);
		return a;
	}
	public static void main(String[] args) {
		DayRunner.run(new Day03());
	}

}
