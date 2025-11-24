package mycode.jobspring.masina.service;

import mycode.jobspring.mappers.JobSpringMapper;
import mycode.jobspring.masina.dtos.MasinaListResponse;
import mycode.jobspring.masina.dtos.MasinaResponse;
import mycode.jobspring.masina.models.Masina;
import mycode.jobspring.masina.repository.MasinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasinaQuerryServiceImpl implements MasinaQuerryService {
    private final MasinaRepository masinaRepository;
    private final JobSpringMapper mapper;

    public MasinaQuerryServiceImpl(MasinaRepository masinaRepository, JobSpringMapper mapper) {
        this.masinaRepository=masinaRepository;
        this.mapper = mapper;
    }

    @Override
    public MasinaListResponse findAllMasini() {
        List<Masina> masinaList = masinaRepository.findAll();
        List<MasinaResponse> masinaResponseList = mapper.mapMasinaListToMasinaResponseList(masinaList);
        return new MasinaListResponse(masinaResponseList);
    }
}